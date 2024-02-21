document.addEventListener("DOMContentLoaded", () => {
    let items = document.querySelectorAll("#div-items");
    items.forEach(item => {
        item.addEventListener("mouseover", () => {
            item.style.color = "red";
        });
        item.addEventListener("mouseout", () => {
            item.style.color = ""; 
        });
    });
    let log_out = document.getElementsByClassName("log-out")[0]; // [0] to access the first element with the class
    log_out.addEventListener("click", () => {
        window.location.href = "index.html";
    });
});
