const zero = 110;
const rConstLength = 80;
let x;
let y;
let r;
let reserveCtx;
let currentX = 0;
let currentR = 0;

function setCurrentX(newX) {
    currentX = newX;
    let radioBtn = document.getElementById("form:selectRadio:"+(Number(newX) + 3).toString()); // CAN BE MORE CRINGE?
    radioBtn.checked = true;
}

function getCurrentX() {
    return currentX;
}

function setCurrentR(newR) {
    currentR = newR;
}

function getCurrentR() {
    return currentR;
}

function loadCtxURL(ctx) {
    let canvasPic = new Image();
    canvasPic.src = reserveCtx;
    canvasPic.onload = function() { ctx.drawImage(canvasPic, 0, 0) };
}

function clearCanvas() {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}


function draw() {
    const canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        canvas.addEventListener('click', canvasClicked, false);

        function canvasClicked(e) {
            let element = document.getElementById('canvas');
            let topPos = element.getBoundingClientRect().top + window.scrollY;
            let leftPos = element.getBoundingClientRect().left + window.scrollX;

            x = e.pageX - leftPos - zero;
            y = zero - (e.pageY - topPos);

            r = getCurrentR();
            if (r !== null) {
                x = Math.round(x / rConstLength * r);
                y = (y / rConstLength * r).toFixed(5);

                if (contains([-3, -2, -1, 0, 1, 2, 3, 4, 5], x) && (-3 <= y) && (y <= 3)) {
                    const YField = document.getElementById('form:y_chooser');
                    YField.value = y;
                    setCurrentX(x);

                    pointDraw()
                }
            } else alert("Выберете радиус");
        }
    }
}


function pointDraw() {
    const canvas = document.getElementById('canvas');
    let xCanvas = zero + x/r*rConstLength;
    let yCanvas = zero - y/r*rConstLength;

    if (canvas.getContext) {
        const ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        loadCtxURL(ctx);
        ctx.beginPath();

        if (checkHit(x, y, r)) {
            ctx.fillStyle = "#2DCF00";
        } else {
            ctx.fillStyle = "#FF2A1F";
        }
        ctx.moveTo(125, 35);
        ctx.arc(xCanvas, yCanvas, 3, 0, 2 * Math.PI);
        ctx.fill();
    }
}


function pointOldArc(x, y, r) {
    const canvas = document.getElementById('canvas');
    let xCanvas = zero + x/r*rConstLength;
    let yCanvas = zero - y/r*rConstLength;

    if (canvas.getContext) {
        const ctx = canvas.getContext('2d');
        ctx.beginPath();

        if (checkHit(x, y, r)) {
            ctx.fillStyle = "#2DCF00";
        } else {
            ctx.fillStyle = "#FF2A1F";
        }
        ctx.moveTo(125, 35);
        ctx.arc(xCanvas, yCanvas, 3, 0, 2 * Math.PI);
        ctx.fill();
        ctx.save();
        reserveCtx = canvas.toDataURL();
    }
}

function update() {
    r = getCurrentR().toString();
    y = document.getElementById('form:y_chooser').value.trim().replace(",", ".");
    x = getCurrentX().toString();

    if (contains(["-3", "-2", "-1", "0", "1", "2", "3", "4", "5"], x)
        && (y !== "") && (-3 <= y) && (y <= 3) &&
        contains(['1', '2', '3', '4', '5'], r)) pointDraw();
    else {
        const canvas = document.getElementById('canvas');
        if (canvas.getContext) {
            const ctx = canvas.getContext('2d');
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            loadCtxURL(ctx);
        }
    }
}

function redrawWithNewR() {
    console.log("asdas");
    let table = document.getElementById('resultTable');
    for (let r = 1, n = table.rows.length; r < n; r++) {
        if (table.rows[r].cells[1].innerHTML.trim() !== "") {
            console.log(table.rows[r].cells[0].innerHTML.trim(), table.rows[r].cells[1].innerHTML.trim(), getCurrentR());
            pointOldArc(table.rows[r].cells[0].innerHTML.trim(), table.rows[r].cells[1].innerHTML.trim(), getCurrentR());
        }
    }
}


function contains(a, obj) {
    for (let i = 0; i < a.length; i++) {
        if (a[i] === obj)
            return true;
    }
    return false;
}

function reload() {
    document.location = document.location;
    draw();
    redrawWithNewR()
}

function checkHit(X, Y, R) {
    return ((X >= -R && X <= 0 && Y >= -R/2 && Y <= 0) ||
        (-2*Y <= (-X + R) && Y <= 0 && X >= 0) ||
        ((X*X + Y*Y) <= R*R && X <= 0 && Y >= 0));
}