let binOperation = f => (a, b) => (...args) => f(a(...args), b(...args));

let unOperation = f => a => (...args) => f(a, ...args);

let variable = unOperation((a, ...args) => {
    switch (a) {
        case 'x':
            return (args[0]);
        case 'y':
            return (args[1]);
        case 'z':
            return (args[2]);
    }
});

let cnst = unOperation((a) => a);
let negate = unOperation((a, ...args) => -a(...args));

let pi = cnst(Math.PI);
let e = cnst(Math.E);

let multiply = binOperation((a, b) => a * b);
let add = binOperation((a, b) => a + b);
let subtract = binOperation((a, b) => a - b);
let divide = binOperation((a, b) => a / b);
