var
    canv = $("#canvas"),
    ctx = canv.getContext('2d'),
    isMouseDown = false,
    isPlaying = false,
    savedCoords = [],
    coords = [];
canv.width = 400;
canv.height = 400;

canv.addEventListener('mousedown', function () {
    isMouseDown = true;
});
canv.addEventListener('mouseup', function () {
    isMouseDown = false;
    ctx.beginPath();
    coords.push('mouseup');
});
ctx.lineWidth = 20;
function write(e){
    console.log("Write");
    coords.push([e.clientX, e.clientY]);
    ctx.lineTo(e.clientX, e.clientY);
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(e.clientX, e.clientY, 10, 0, Math.PI * 2);
    ctx.fill();
    ctx.beginPath();
    ctx.moveTo(e.clientX, e.clientY);
}
function game(e){
    if(!savedCoords.length){
        console.log("game false");
        ctx.beginPath();
        isPlaying = false;
        isMouseDown = false;
        return;
    }
    var
        crd = savedCoords.shift(),
        en = {
            clientX: crd["0"],
            clientY: crd["1"]
        };
    if(en.clientX === 'm'){
        ctx.beginPath();
        alert("Congrats!");
        return;
    }
    console.log(en.clientX + " X " + e.clientX);
    console.log(en.clientY + " Y " + e.clientY);
    if ((Math.abs(en.clientX-e.clientX)<60)&&(Math.abs(en.clientY-e.clientY)<60)) {
        ctx.lineTo(e.clientX, e.clientY);
        ctx.stroke();

        ctx.beginPath();
        ctx.arc(e.clientX, e.clientY, 10, 0, Math.PI * 2);
        ctx.fill();
        ctx.beginPath();
        ctx.moveTo(e.clientX, e.clientY);
    }else {
        ctx.beginPath();
        ctx.fillStyle = 'red';
        ctx.arc(e.clientX, e.clientY, 15, 0, Math.PI * 2);
        ctx.fill();
        ctx.fillStyle = 'black';
        console.log("Error");
        isPlaying = false;
        isMouseDown = false;
    }
}
canv.addEventListener('mousemove', function(e){
    if (isMouseDown) {
        if (!isPlaying) {
            write(e);
        } else {
            game(e, coords);
        }
    }
});
function save() {
    localStorage.setItem('coords', JSON.stringify(coords));
    coords = [];
}
function clear(){
    ctx.fillStyle = 'white';
    ctx.fillRect(0, 0, canv.width, canv.height);
    ctx.beginPath();
    ctx.fillStyle = 'black';
}
function replay(){
    var
        timer = setInterval(function () {
            if(!savedCoords.length){
                clearInterval(timer);
                ctx.beginPath();
                return;
            }
            var
                crd = savedCoords.shift(),
                e = {
                    clientX: crd["0"],
                    clientY: crd["1"]
                };
            ctx.lineTo(e.clientX, e.clientY);
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(e.clientX, e.clientY, 10, 0, Math.PI*2);
            ctx.fill();

            ctx.beginPath();
            ctx.moveTo(e.clientX, e.clientY);
        }, 30);
}
document.addEventListener('keydown', function (e) {
    if(e.key === 'c'){
        clear();
    }else if (e.key === 'w'){
        coords = [];
        clear();
    }else if (e.key === 's'){
        console.log("save");
        save();
    }else if (e.key === 'r'){
        clear();
        savedCoords = JSON.parse(localStorage.getItem('coords'));
        replay();
    }else if(e.key === 'g'){
        console.log("Game");
        savedCoords = JSON.parse(localStorage.getItem('coords'));
        clear();
        isPlaying = true;
    }
})