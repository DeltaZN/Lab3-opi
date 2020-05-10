var ctx = myCanvas.getContext("2d");

ctx.font = "10px Verdana";
ctx.lineWidth = 1.5; //толщина линий

var checkBoxR_Container = document.getElementsByName("form:r");

window.onload = function () {
    updateTimezoneOffset();

    let myCanvas = document.getElementById("myCanvas");
    myCanvas.addEventListener("click", function (elem) {
        clickOnCanvas(elem);
    });

    registerRListeners();
    document.getElementById('form:form_input').addEventListener('mouseenter', function(event) {
        event.target.setAttribute('autocomplete', 'off')
    });
};

document.getElementById("form:timezoneOffset_id").value = new Date().getTimezoneOffset();

function updateTimezoneOffset() {
    document.getElementById("form:timezoneOffset_id").value = new Date().getTimezoneOffset();
    document.getElementById("canvas_form:timezoneOffset_id_canvas").value = new Date().getTimezoneOffset();
    setTimeout(updateTimezoneOffset, 9000);
}

function registerRListeners() {
    Array.prototype.slice.call(checkBoxR_Container).forEach( elem => {
        elem.addEventListener("click", function (e) {
            ctx.clearRect(0, 0, 305, 305); //очистка для перерисовки
            console.log("Click on R");
            drawGraph(elem.value);
        });

        if (elem.hasAttribute("checked")) {
            drawGraph(elem.value);
            document.getElementById(elem.id).click();
        }
    });
}

// отрисовка
function clickOnCanvas(elem) {
    // let elem = window.event;
    let br = myCanvas.getBoundingClientRect();
    let left = br.left; // X координата верхнего левого края канваса
    let top = br.top; // Y координата верхнего левого края канваса
    document.getElementById("canvas_form:x_Canvas").value = (elem.clientX - 152 - left) / 30;
    document.getElementById("canvas_form:y_Canvas").value = (-(elem.clientY - top) + 152) / 30;

    addPointFromCanvas(); //вызываем удаленную процедуру по добавлению точки в ArrayList (название метода соотв. значению поля name p:remotecommand)
}


function drawCoordinatePlane() {
    ctx.beginPath();
    ctx.fillStyle = "rgb(60, 16, 44)";
    ctx.moveTo(0, 150);
    ctx.lineTo(305, 150); //ось ОХ
    ctx.fillText("X", 290, 140);
    //стрелочка на оси ОХ
    ctx.moveTo(305, 150);
    ctx.lineTo(300, 145);
    ctx.moveTo(305, 150);
    ctx.lineTo(300, 155);

    //ось Y
    ctx.moveTo(150, 0);
    ctx.lineTo(150, 305);
    ctx.fillText("Y", 160, 10);
    //стрелочка на оси Y
    ctx.moveTo(150, 0);
    ctx.lineTo(155, 5);
    ctx.moveTo(150, 0);
    ctx.lineTo(145, 5);
    ctx.stroke();
}

function drawNumbers() {
    //деления на ОY
    ctx.beginPath();
    ctx.fillStyle = "rgb(60, 16, 44)";
    ctx.strokeStyle = "rgb(60, 16, 44)";
    ctx.moveTo(145, 5);
    ctx.lineTo(155, 5);
    ctx.fillText(5, 160, 0);
    ctx.moveTo(145, 30);
    ctx.lineTo(155, 30);
    ctx.fillText(4, 160, 33);
    ctx.moveTo(145, 60);
    ctx.lineTo(155, 60);
    ctx.fillText(3, 160, 63);
    ctx.moveTo(145, 90);
    ctx.lineTo(155, 90);
    ctx.fillText(2, 160, 93);
    ctx.moveTo(145, 120);
    ctx.lineTo(155, 120);
    ctx.fillText(1, 160, 123);
    ctx.fillText(0, 140, 163); // ноль в центре координат
    ctx.moveTo(145, 180);
    ctx.lineTo(155, 180);
    ctx.fillText(-1, 160, 183);
    ctx.moveTo(145, 210);
    ctx.lineTo(155, 210);
    ctx.fillText(-2, 160, 213);
    ctx.moveTo(145, 240);
    ctx.lineTo(155, 240);
    ctx.fillText(-3, 160, 243);
    ctx.moveTo(145, 270);
    ctx.lineTo(155, 270);
    ctx.fillText(-4, 160, 273);
    ctx.moveTo(145, 300);
    ctx.lineTo(155, 300);
    ctx.fillText(-5, 160, 303);

    //Деления на OX

    ctx.moveTo(2, 145);
    ctx.lineTo(2, 155);
    ctx.fillText(-5, 0, 163);
    ctx.moveTo(30, 145);
    ctx.lineTo(30, 155);
    ctx.fillText(-4, 25, 163);
    ctx.moveTo(60, 145);
    ctx.lineTo(60, 155);
    ctx.fillText(-3, 55, 163);
    ctx.moveTo(90, 145);
    ctx.lineTo(90, 155);
    ctx.fillText(-2, 85, 163);
    ctx.moveTo(120, 145);
    ctx.lineTo(120, 155);
    ctx.fillText(-1, 115, 163);

    ctx.moveTo(180, 145);
    ctx.lineTo(180, 155);
    ctx.fillText(1, 177, 163);
    ctx.moveTo(210, 145);
    ctx.lineTo(210, 155);
    ctx.fillText(2, 207, 163);
    ctx.moveTo(240, 145);
    ctx.lineTo(240, 155);
    ctx.fillText(3, 237, 163);
    ctx.moveTo(270, 145);
    ctx.lineTo(270, 155);
    ctx.fillText(4, 267, 163);
    ctx.moveTo(300, 145);
    ctx.lineTo(300, 155);
    ctx.fillText(5, 297, 163);

    ctx.stroke();
}

function drawFigures(selectedR_value) {
    //квадрат
    ctx.beginPath();
    ctx.rect(150, 150, -selectedR_value * 15, -selectedR_value * 30);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

//треугольник
    ctx.beginPath();
    ctx.moveTo(150 + (selectedR_value * 30), 150);
    ctx.lineTo(150, 150 - selectedR_value * 30);
    ctx.lineTo(150, 150);
    ctx.lineTo(150 - (selectedR_value * 15), 150);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

//полукруг
    ctx.beginPath();
    ctx.moveTo(150, 150);
    ctx.arc(150, 150, selectedR_value * 15, 0, Math.PI / 2, false);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    drawCoordinatePlane();
}


function drawPoint(x, y, hit) {

    ctx.beginPath();
   // ctx.arc(150 + x * 30, 150 - y * 30, 1, 0, 2 * Math.PI, true);
    //костыль
    ctx.moveTo(150 + x * 30, 150 - y * 30);
    ctx.lineTo(150 + x * 30, 140 - y * 30);
    ctx.lineTo(154 + x * 30, 130 - y * 30);
    ctx.lineTo(154 + x * 30, 110 - y * 30);
    ctx.lineTo(146 + x * 30, 110 - y * 30);
    ctx.lineTo(146 + x * 30, 130 - y * 30);
    ctx.lineTo(150 + x * 30, 140 - y * 30);
    ctx.moveTo(154 + x * 30, 130 - y * 30);
    ctx.lineTo(146 + x * 30, 130 - y * 30);
    ctx.closePath();

    let color = 'red';

    if (hit) {
        color = 'lime';
    }

    ctx.strokeStyle = color;
    ctx.fillStyle = color;
    //ctx.fill();
    ctx.stroke();
}

function drawGraph(r) {
    ctx.clearRect(0, 0, 305, 305);
    ctx.fillStyle = "rgb(0,0,255)";
    ctx.strokeStyle = "rgb(60, 16, 44)";

    drawCoordinatePlane(); //отрисовка координатных прямых
    ctx.fillStyle = "rgb(0,0,255)";
    drawFigures(r);
    drawNumbers();

}

Array.prototype.slice.call(document.querySelectorAll('table#form\\:x td')).forEach(e =>
    e.classList.add("radio") )


Array.prototype.slice.call(document.querySelectorAll('table#form\\:r td')).forEach(e =>
    e.classList.add("radio") )


// document.querySelectorAll('table.answer_table tr td').forEach(e =>
//     e.classList.add("tdScroll") )
