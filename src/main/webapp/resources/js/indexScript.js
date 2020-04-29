window.onload = function () {
    updateClock();
    document.getElementById('form_toMain:login').addEventListener('mouseenter', function(event) {
        event.target.setAttribute('autocomplete', 'off')
    });
};

function updateClock() {
    let now = new Date();
    let timeString = [now.getHours(), now.getMinutes(), now.getSeconds()].join(':');
    let dateString = [now.getDate(), now.getMonth() + 1, now.getFullYear()].join('.');
    document.getElementById('clock').innerHTML = [dateString, timeString].join(' | ');
    setTimeout(updateClock, 9000);
}

