window.onload = function () {
    let clock = document.getElementById("time");
    clock.innerHTML = new Date().toLocaleTimeString();
    window.setInterval(function () {
        clock.innerHTML = new Date().toLocaleTimeString();
        document.getElementById("timerRect").style.color = "#" + Math.floor(Math.random()*16777215).toString(16); // Рандомный цвет
        }, 9000);
};