window.onload = function () {
    event = event || window.event;
    var pageX = event.pageX;
    var pageY = event.pageY;
    if (pageX === undefined) {
        pageX = event.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
        pageY = event.clientY + document.body.scrollTop + document.documentElement.scrollTop;
    }
    document.getElementById("div1").addEventListener("mousemove", () => {
        console.log(pageX + " " +pageY);
    }, true);
}