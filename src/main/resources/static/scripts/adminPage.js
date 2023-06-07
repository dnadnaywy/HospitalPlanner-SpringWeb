async function logJSONData() {
    const response = await fetch("http://localhost:7010/admin/tables");
    try {
        let button = document.querySelector('.button-return-main');
        button.style.display = 'block';
        let container = document.querySelector('.container-metadata-tables');
        container.style.display = 'flex';

        const jsonData = await response.json();
        let html = '';
        jsonData.forEach(table => {
            let htmlSegment = `<div class="table-box">
                                    <i class="fa-solid fa-database"></i>
                                    <h3>${table.tableName}</h3>
                                    <div class="table-box-smaller-text">Number of Rows: ${table.nrOfRows}</div>
                                    <div class="table-box-smaller-text">Nested table: ${table.nested}</div>
                                  </div>
                                    `;
            html += htmlSegment;
        });

        container = document.querySelector('.container-metadata-tables');
        let buttons = document.querySelectorAll('.buttons-header-doctors');
        for (var x = 0; x < buttons.length; x++)
            buttons[x].style.display = 'none';
        //adding the things created into the actual page
        container.innerHTML = html;

        //now let's add the button for returning to the four buttons
        html = '';
        let htmlSegment = `<button type="button" onclick="returnToMainContent()" id="return-to-main-content-button">Return and choose<br>another option</button>`;
        html += htmlSegment;
        button = document.querySelector('.button-return-main');
        button.innerHTML = html;

        console.log(jsonData);
        return await jsonData;
    } catch (error) {
        console.log(error);
    }
}

function showAlert() {
    alert("The button was clicked!");
}

function returnToMainContent() {
    let button = document.querySelector('.button-return-main');
    button.style.display = 'none';
    let container = document.querySelector('.container-metadata-tables');
    container.style.display = 'none';
    let buttons = document.querySelectorAll('.buttons-header-doctors');
    for (var x = 0; x < buttons.length; x++)
        buttons[x].style.display = 'block';
}

async function displayDataFromTable() {
    const response = await fetch("http://localhost:7010/admin/tablesData");
    try {
        let html = '';
        let htmlSegment = `<button type="button" onclick="returnToMainContent()" id="return-to-main-content-button">Return and choose<br>another option</button>`;
        html += htmlSegment;
        button = document.querySelector('.button-return-main');
        button.innerHTML = html;

        console.log(jsonData);
        return await jsonData;
    } catch (error) {
        console.log(error);
    }
}

function redirectToAdminAddUser() {
    location.href="http://localhost:7010/adminAddUser";
}