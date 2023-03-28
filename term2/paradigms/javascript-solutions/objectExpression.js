function BinOperation(a, b, sign, f) {
    this.a = a;
    this.b = b;
    this.sign = sign;
    this.f = f;
    this.toString = () => (this.a + " " + this.b + " " + this.sign)
    this.evaluate = (x, y, z) => (this.f(this.a.evaluate(x, y, z), this.b.evaluate(x, y, z)))
    this.prefix = () => ("(" + this.sign + " " + this.a.prefix() + " " + this.b.prefix() +")")
}

function ConstVarOperation(a) {
    this.a = a;
    this.evaluate = (x, y, z) => {
        switch (this.a) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return Number(this.a);
        }
    }
    this.toString = () => (String (this.a))
    this.prefix = () => (String (this.a))
}

function UnOperation(a, sign, f) {
    this.a = a;
    this.sign = sign;
    this.f = f;
    this.evaluate = (x, y, z) => (this.f(this.a.evaluate(x, y, z)))
    this.toString = () => (this.a + " " + this.sign)
    this.prefix = () => ("(" + this.sign + " " + this.a.prefix() + ")")
}

function Multiply(a, b) { return new BinOperation(a, b, "*", (a, b) => a * b);}
function Divide(a, b) { return new BinOperation(a, b, "/", (a, b) => a / b);}
function Subtract(a, b) { return new BinOperation(a, b, "-", (a, b) => a - b);}
function Add(a, b) { return new BinOperation(a, b, "+", (a, b) => a + b);}
function Variable(a) { return new ConstVarOperation(a);}
function Const(a) { return new ConstVarOperation(a);}
function Negate(a) { return new UnOperation(a, "negate", a => -a);}
function Sinh(a) { return new UnOperation(a, "sinh", Math.sinh);}
function Cosh(a) { return new UnOperation(a, "cosh", Math.cosh);}

function ParseError(error, expr) {
    this.error = error + ' in expression: ' + expr;
}
ParseError.prototype = Object.create(Error.prototype);

const BinOp = {"+": Add, "*": Multiply, "-": Subtract, "/": Divide}

function parse(str, flag = 0) {
    let s = str;
    checkBracket(str)
    str = makeArr(str);
    if (flag === 1) {str = str.reverse();}
    let a = [];
    let cnt = 0;
    for (let i = 0; i < str.length; i++) {
        if (!isNaN((str[i]))) {a.push(new Const(str[i])); cnt++; continue;}
        switch (str[i]) {
            case ")": break;
            case "(": if (str[i - 2] === ")" || str[i - 1] === ")") {
                throw new ParseError("Wrong Expression in brackets", s);}
                break;
            case "x":
            case "y":
            case "z": a.push(new Variable(str[i])); cnt++; break;
            case "+":
            case "-":
            case "*":
            case "/":
                let tmp = a.pop()
                flag === 0 ? a.push(BinOp[str[i]](a.pop(), tmp)) : a.push(BinOp[str[i]](tmp, a.pop()));
                cnt--;
                break;
            case "negate":a.push(new Negate(a.pop()));break;
            case "sinh":a.push(new Sinh(a.pop()));break;
            case "cosh":a.push(new Cosh(a.pop()));break;
            default: throw new ParseError("Unexpected symbol", s);
        }
    }
    if (cnt !== 1) {throw new ParseError("Wrong math expression", s);}
    return a.pop()
}

function checkBracket(str) {
    let count = 0;
    for (let i = 0; i < str.length; i++) {
        if (str[i] === "(") {count++;}
        if (str[i] === ")") {count--;}
    }
    if (count !== 0) {throw new ParseError("Wrong bracket sequence", str);}
}

function makeArr(str) {
    str = str.replaceAll("(", " ( ").replaceAll(")", " ) ");
    str = String(str).trim().split(" ");
    str = str.filter((n) => n !== "");
    return str;
}

const parsePrefix = (str) => parse(str, 1)