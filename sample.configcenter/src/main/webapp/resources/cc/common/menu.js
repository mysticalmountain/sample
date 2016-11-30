// $(document).ready(function () {

$("#linkUser").click(function () {
    alert('link user');
    location.href = "/views/user/list.js";
});
$("#linkRole").click(function () {
    alert('33333333');
    location.href = "/views/role/list.js";
});
$("#header").load("/views/common/header.html");
$("#footer").load("/views/common/footer.html");
$("#navbar").load("/views/common/navbar.html");
$("#menu").load("/views/common/menu.html");
// });
