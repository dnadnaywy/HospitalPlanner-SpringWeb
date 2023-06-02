async function logJSONData() {
    const response = await fetch("http://localhost:7010/admin/tables");
    const jsonData = await response.json();
    console.log(jsonData);
}

function showAlert() {
    alert("The button was clicked!");
}