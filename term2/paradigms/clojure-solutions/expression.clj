(defn binOp [sign args] (fn [x] (reduce sign (mapv (fn [y] (y x)) args))))

(defn divide [& args] (fn [x] (/ (double ((first args) x)) (reduce * (mapv (fn [y] (y x)) (rest args))))))
(defn add [& args] (binOp + args))
(defn subtract [& args] (binOp - args))
(defn multiply [& args] (binOp * args))
(defn negate [a] (fn [x] (- (a x))))
(defn constant [a] (fn [x] a))
(defn variable [x] (fn [a] (get a x)))
(defn pow [a b] (fn [x] (Math/pow (a x) (b x))))
(defn log [a b] (fn [x] (/ (Math/log (Math/abs (b x))) (Math/log (Math/abs (a x))))))
(def var #{"x" "y" "z"})

(def FunctionSign {
                   '+      add
                   '-      subtract
                   '*      multiply
                   'negate negate
                   '/      divide
                   'log    log
                   'pow    pow})
(defn parse [s]
  (cond
    (number? s) (constant s)
    (contains? var (str s)) (variable (str s))
    :else (apply (get FunctionSign (peek s)) (map parse (pop s)))
    )
  )


(defn parseFunction [s] (parse (read-string s)))

(defn proto-get
  ([obj key] (proto-get obj key nil))
  ([obj key default]
   (cond
     (contains? obj key) (obj key)
     (contains? obj :prototype) (proto-get (obj :prototype) key default)
     :else default)))


(defn proto-call
  [this key & args]
  (apply (proto-get this key) this args))

(defn method
  [key] (fn [this & args] (apply proto-call this key args)))

(def evaluate (method :evaluate))
(def toStringSuffix (method :toStringSuffix))

(def toString (method :toString))
(defn Mln [a] (Math/log (Math/abs a)))

(defn Neg [a] (- a))
(def EvalOpMap {
                'ln     Mln
                'negate Neg
                })

(defn Op [sign] (fn [a] {:sign     sign :val a
                         :evaluate (fn [expr vars]
                                     (cond
                                       (= (expr :sign) 'const) (expr :val)
                                       (= (expr :sign) 'var) (vars (str (nth (clojure.string/lower-case (expr :val)) 0)))
                                       :else ((EvalOpMap (expr :sign)) (evaluate (expr :val) vars))
                                       ))
                         :toString (fn [expr]
                                     (cond
                                       (= (expr :sign) 'negate) (str "(negate " (toString (expr :val)) ")")
                                       :else (str (expr :val))
                                       ))
                         :toStringSuffix (fn [expr]
                                     (cond
                                       (= (expr :sign) 'negate) (str "(" (toStringSuffix (expr :val)) " negate)")
                                       :else (str (expr :val))
                                       ))
                         }))

(defn mPow [a b] (Math/pow a b))
(defn mLog [a b] (/ (Math/log (Math/abs b)) (Math/log (Math/abs a))))
(defn MD [a b] (/ (double a) b))
(def EvalMap {
              '+   +
              '-   -
              '*   *
              '/   MD
              'log mLog
              'pow mPow
              })

(defn BinOp [sign] (fn [a b]
                     {:sign     sign :a a :b b
                      :evaluate (fn [expr vars]
                                  ((EvalMap (expr :sign)) (evaluate (expr :a) vars) (evaluate (expr :b) vars)))
                      :toString (fn [expr] (str "(" (expr :sign) " " (toString (expr :a)) " " (toString (expr :b)) ")"))
                      :toStringSuffix (fn [expr] (str "("  (toStringSuffix (expr :a)) " " (toStringSuffix (expr :b)) " " (expr :sign)  ")"))
                      }))

(def Constant (Op 'const))
(def Variable (Op 'var))
(def Negate (Op 'negate))
(def Add (BinOp '+))
(def Subtract (BinOp '-))
(def Multiply (BinOp '*))
(def Divide (BinOp '/))
(def Pow (BinOp 'pow))
(def Log (BinOp 'log))
(def Ln (Op 'ln))
(def ObjectSign {'+      Add
                 '-      Subtract
                 '*      Multiply
                 'negate Negate
                 '/      Divide
                 'pow    Pow
                 'log    Log})

(defn parser [s]
  (cond
    (number? s) (Constant s)
    (contains? var (str s)) (Variable (str s))
    :else (apply (get ObjectSign (peek s)) (map parser (pop s)))
    )
  )

(defn parseObject [s] (parser (read-string s)))

(defn diff [expr vars]
  (cond
    (= (expr :sign) 'log) (diff (Divide (Ln (expr :b)) (Ln (expr :a))) vars)
    (= (expr :sign) 'ln) (Divide (diff (expr :val) vars) (expr :val))
    (= (expr :sign) 'pow) (Multiply (Pow (expr :a) (expr :b)) (diff (Multiply (Ln (expr :a)) (expr :b)) vars))
    (= (expr :sign) 'const) (Constant 0)
    (= (expr :sign) 'var) (if (= vars (str (nth (clojure.string/lower-case (expr :val))0))) (Constant 1) (Constant 0))
    (= (expr :sign) 'negate) (Negate (diff (expr :val) vars))
    (= (expr :sign) '*) (Add (Multiply (diff (expr :a) vars) (expr :b)) (Multiply (expr :a) (diff (expr :b) vars)))
    (= (expr :sign) '/) (Divide (Subtract (Multiply (diff (expr :a) vars) (expr :b))
                                          (Multiply (expr :a) (diff (expr :b) vars))) (Multiply (expr :b) (expr :b)))
    :else ((ObjectSign (expr :sign)) (diff (expr :a) vars) (diff (expr :b) vars))
    )
  )

(load-file "parser.clj")

(def parseObjectSuffix
  (let [*all-chars (mapv char (range 32 128))
        *space (+char (apply str (filter #(Character/isWhitespace %) *all-chars)))
        *ws (+ignore (+star *space))
        *digit (+char "0123456789.")
        *number (+seqf (comp Constant read-string) (+str (+seq (+opt (+char "-"))(+str (+plus *digit)))))
        *var (+seqf (comp Variable #(apply str %)) (+plus (+char "xyzXYZ")))
        *add (+seqf (constantly Add) (+char "+"))
        *subtract (+seqf (constantly Subtract) (+char "-"))
        *divide (+seqf (constantly Divide) (+char "/"))
        *multiply (+seqf (constantly Multiply) (+char "*"))
        *negate (+seqf (constantly Negate) (+seq (+char "n")(+char "e")(+char "g")(+char "a")(+char "t")(+char "e")))]
    (letfn [(*seq [begin p end] (+seqn 1 (+char begin) (+opt (+seqf cons *ws p (+star (+seqn 0 *ws p)))) *ws (+char end)))
            (*list [] (+map #(apply (last %) ((comp reverse pop reverse) %)) (*seq "(" (delay (*value)) ")")))
            (*value [] (+or  *number *var *add *multiply *divide *subtract *negate (*list)))]
      (+parser (+seqn 0 *ws (*value) *ws)))))