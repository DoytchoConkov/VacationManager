let allVacations = $('#all');
let pendingVacations = $('#pending');
let acceptedVacations = $('#accepted');
let rejectedVacations = $('#rejected');
let table = $('#table');
let tableBody = $('#tableBody');

allVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['PENDING', 'ACCEPTED', 'REJECTED'])
        .then((response) => response.json())
        .then((v) => {
                tableBody.empty();
                v.forEach((o) => {
                    let thisBeginDate = o.beginDate.toString().split('-');
                    let newBeginDate = [thisBeginDate[2],thisBeginDate[1],thisBeginDate[0] ].join("-");
                    let thisEndDate = o.endDate.toString().split('-');
                    let newEndDate = [thisEndDate[2],thisEndDate[1],thisEndDate[0] ].join("-");
                    let row = `<tr>
                     <td>${newBeginDate}</td>
                     <td>${newEndDate}</td>
                     <td>${o.comment}</td>
                     <td>${capitalizeFirstLetter(o.status.toLowerCase())}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                    tableBody.append(row);
                })
        })
})

pendingVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['PENDING'])
        .then((response) => response.json())
        .then((v) => {
            tableBody.empty();
            v.forEach((o) => {
                let thisBeginDate = o.beginDate.toString().split('-');
                let newBeginDate = [thisBeginDate[2],thisBeginDate[1],thisBeginDate[0] ].join("-");
                let thisEndDate = o.endDate.toString().split('-');
                let newEndDate = [thisEndDate[2],thisEndDate[1],thisEndDate[0] ].join("-");
                let row = `<tr>
                     <td>${newBeginDate}</td>
                     <td>${newEndDate}</td>
                     <td>${o.comment}</td>
                     <td>${capitalizeFirstLetter(o.status.toLowerCase())}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})

acceptedVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['ACCEPTED'])
        .then((response) => response.json())
        .then((v) => {
            tableBody.empty();
            v.forEach((o) => {
                let thisBeginDate = o.beginDate.toString().split('-');
                let newBeginDate = [thisBeginDate[2],thisBeginDate[1],thisBeginDate[0] ].join("-");
                let thisEndDate = o.endDate.toString().split('-');
                let newEndDate = [thisEndDate[2],thisEndDate[1],thisEndDate[0] ].join("-");
                let row = `<tr>
                     <td>${newBeginDate}</td>
                     <td>${newEndDate}</td>
                     <td>${o.comment}</td>
                     <td>${capitalizeFirstLetter(o.status.toLowerCase())}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})

rejectedVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['REJECTED'])
        .then((response) => response.json())
        .then((v) => {
            tableBody.empty();
            v.forEach((o) => {
                let thisBeginDate = o.beginDate.toString().split('-');
                let newBeginDate = [thisBeginDate[2],thisBeginDate[1],thisBeginDate[0] ].join("-");
                let thisEndDate = o.endDate.toString().split('-');
                let newEndDate = [thisEndDate[2],thisEndDate[1],thisEndDate[0] ].join("-");
                let row = `<tr>
                     <td>${newBeginDate}</td>
                     <td>${newEndDate}</td>
                     <td>${o.comment}</td>
                     <td>${capitalizeFirstLetter(o.status.toLowerCase())}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}