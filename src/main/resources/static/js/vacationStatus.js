let allVacations = $('#all');
let pendingVacations = $('#pending');
let acceptedVacations = $('#accepted');
let rejectedVacations = $('#rejected');
let table = $('#table');
let tableBody = $('#tableBody');

allVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['Pending...', 'Accepted', 'Rejected'])
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
                     <td>${o.status}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                    tableBody.append(row);
                })
        })
})

pendingVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['Pending...'])
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
                     <td>${o.status}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})

acceptedVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['Accepted'])
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
                     <td>${o.status}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})

rejectedVacations.click(() => {
    fetch('http://localhost:8080/vacations/filter-vacation?vacationStatus=' + ['Rejected'])
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
                     <td>${o.status}</td>
                     <td>${o.username}</td>
                                   </tr>`;
                tableBody.append(row);
            })
        })
})