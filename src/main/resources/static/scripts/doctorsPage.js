var form = "";
var firstName = "";
var lastName = "";
var fullName = "";


var submitButton = document.getElementById("submitButton");

// Add click event listener
submitButton.addEventListener("click", function (event) {
    event.preventDefault(); // Prevent form submission

    // Perform your desired actions here
    console.log("Submit button clicked");
    // showAlert();

    form = document.getElementById('loginDoctor');
    firstName = form.elements['firstName'];
    lastName = form.elements['lastName'];

    fullName = lastName.value + " " + firstName.value;
    console.log(fullName);

    // You can also submit the form programmatically if needed
    document.getElementById("loginDoctor").submit();
});

function redirectToDoctorViewSchedule() {
    location.href = "http://localhost:7010/doctors/schedule";
}

function showAlert() {
    alert("The button was clicked!");
}

async function displayScheduleByName() {//TODO: sth is worng soewhere
    console.log(fullName);
    const response = await fetch("http://localhost:7010/doctors/" + fullName);
    try {
        let button = document.querySelector('.button-return-main');
        button.style.display = 'block';
        let container = document.querySelector('.container-view-schedule');
        container.style.display = 'grid';

        const jsonData = await response.json();
        let html = '<div class="schedule-special-text">Your schedule this week:</div>';
        jsonData.forEach(schedule => {
            let htmlSegment = `<div class="table-box">
                                        <i class="fa-solid fa-calendar-week"></i>
                                        <div class="table-box-smaller-text">Day of the appointment: <b>${schedule.day}</b></div>
                                        <div class="table-box-smaller-text">Hour of the appointment: <b>${schedule.hour}:${schedule.minute}</b></div>
                                    </div>
                                    `;
            html += htmlSegment;
        });

        container = document.querySelector('.container-view-schedule');
        let buttons = document.querySelectorAll('.buttons-header-doctors');
        for (var x = 0; x < buttons.length; x++)
            buttons[x].style.display = 'none';
        // adding the things created into the actual page
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

function returnToMainContent() {
    let button = document.querySelector('.button-return-main');
    button.style.display = 'none';
    let container = document.querySelector('.container-view-schedule');
    container.style.display = 'none';
    let buttons = document.querySelectorAll('.buttons-header-doctors');
    for (var x = 0; x < buttons.length; x++)
        buttons[x].style.display = 'block';
}