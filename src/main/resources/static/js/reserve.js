function checkRepeated() {
    let untilDateForm = document.getElementById('inputUntilDate');

    if (document.getElementById('isCheckUntil').checked === true) {
        untilDateForm.disabled = false;
    } else {
        untilDateForm.disabled = true;
        untilDateForm.value = null;
    }
}

function submit() {
    let reservation = {
        'owner': document.getElementById('inputName').value,
        'room': {
            'name': document.getElementById('selectRoom').value
        },
        'period': {
            'start': document.getElementById('inputStartDate').value,
            'end': document.getElementById('inputEndDate').value
        }
    };

    let until = document.getElementById('inputUntilDate').value;
    if (until !== "") {
        reservation.until = until;
    }

    console.log(reservation);

    return new FetchManager({
        url: '/api/rooms/reserve',
        method: 'POST',
        body: JSON.stringify(reservation),
    }).onSuccess(response => {
        location.href = '/';
    }).onFailed(errors => {
        errors.error.forEach(item => {
            alert(item.message);
        });
    }).doFetch()
}