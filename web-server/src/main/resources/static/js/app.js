
function greetings() {
    var inputValue = $("#input-id").val();
    if (!inputValue) {
        $("#button-pressed").text("Error! Please insert a value");
    } else {
        $("#button-pressed").text(inputValue);

    }
}