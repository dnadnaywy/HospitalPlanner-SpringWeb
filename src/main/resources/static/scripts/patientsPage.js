function showAlert() {
    alert("The button was clicked!");
}

async function displayAllDoctors() {
    const response = await fetch("http://localhost:7010/doctors/allDoctors");
    try {
        let button = document.querySelector('.button-return-main');
        button.style.display = 'block';
        let container = document.querySelector('.container-doctor-list');
        container.style.display = 'grid';

        const jsonData = await response.json();
        let html = '    <div class="schedule-special-text">Meet the doctors from our hospital:</div>\n';
        jsonData.forEach(doctor => {
            let htmlSegment = `<div class="table-box">
                                        <i class="fa-solid fa-user"></i>
                                        <h3 class="doctor-name">Dr. ${doctor.name}</h3>
                                        <div class="table-box-smaller-text">Medic ${doctor.medical_grade}</div>
                                        <div class="table-box-smaller-text">Specialization: ${doctor.specialization}</div>
                                        <div class="table-box-smaller-text">Doctor id (remember it please): ${doctor.id}</div>
                                        <button type="button" th:onclick="showAlert()" class="make-appointment">Make an appointment</button>
                                    </div>
                                    `;
            html += htmlSegment;
        });

        container = document.querySelector('.container-doctor-list');
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

function returnToMainContent() {
    let button = document.querySelector('.button-return-main');
    button.style.display = 'none';
    let container = document.querySelector('.container-doctor-list');
    container.style.display = 'none';
    let buttons = document.querySelectorAll('.buttons-header-doctors');
    for (var x = 0; x < buttons.length; x++)
        buttons[x].style.display = 'block';
}

document.addEventListener('DOMContentLoaded', function() {
    const parentContainer = document.querySelector('.container-doctor-list');

    parentContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('make-appointment')) {
            // showAlert();
            redirectToPatientAppointment();
            // const form = document.getElementById('loginDoctor');
            // const firstName = form.elements['firstName'];
            // const lastName = form.elements['lastName'];
            //
            // let fullName = lastName.value;
            // console.log(fullName);
        }
    });
});

function redirectToPatientAppointment() {
    location.href = "http://localhost:7010/appointment";
}
