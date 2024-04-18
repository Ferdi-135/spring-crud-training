/**
 * Function for creating a new student to the system.
 */
function create() {

    // alert('Create button clicked');
    
    // 1. Get input values
    var input1 = $('#createInput1').val();
    var input2 = $('#createInput2').val();
    var input3 = $('#createInput3').val();

     // 2. Create data object to send to the server
     var data = {
        vorname: input1,
        nachname: input2,
        geburtstag: input3
    };

    // check format
    console.log("Will send data as JSON: " + JSON.stringify(data));

    // 3. Send POST request to server
    $.ajax({
        url: '/create_student', // choose correct endpoint
        type: 'POST', // choose correct type
        contentType: 'application/json', // Set content type to JSON
        data: JSON.stringify(data), // Convert data object to JSON string
        success: function(response) {
            // Handle success response
            console.log('Create operation successful');
            // show result to user
            $('#createResponse').text('Server Response: ' + JSON.stringify(response));
            getEverythingAsTable();
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error:', error);
            // show result to user
            $('#createResponse').text('Error: ' + error);

        }
    });

    
}

function update() {
    // Logic to handle update operation
    //alert('Update button clicked');

    // 1. Get input values
    var input1 = $('#updateInput1').val();
    var input2 = $('#updateInput2').val();
    var input3 = $('#updateInput3').val();
    var input4 = $('#updateInput4').val();

    // 2. Create data object to send to the server
    var data = {
        id: input1,
        vorname: input2,
        nachname: input3,
        geburtstag: input4
    };

    // 3. Send POST request to server
    $.ajax({
        url: '/update_student/' + input1, // choose correct endpoint
        type: 'POST', // choose correct type
        contentType: 'application/json', // Set content type to JSON
        data: JSON.stringify(data), // Convert data object to JSON string
        success: function(response) {
            // Handle success response
            console.log('Update operation successful');
            // show result to user
            $('#updateResponse').text('Server Response: ' + JSON.stringify(response));
            getEverythingAsTable();
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error:', error);
            // show result to user
            $('#updateResponse').text('Error: ' + error);

        }
    });
}

/**
 * Looks up a student and presents result.
 * Can only find one entry.
 */
function search() {
    // Logic to handle search operation
    //alert('Search button clicked');

    // 1. Get input values
    var input1 = $('#searchInput').val();

    // 2. create ajax call
    $.ajax({
        url: '/read_student/' + input1, // choose correct endpoint
        type: 'GET', // choose correct type
        success: function(response) {
            // Handle success response
            console.log('Search operation successful');
            // show result to user
            $('#searchResponse').text('Server Response: ' + JSON.stringify(response));

        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error:', error);
            // show result to user
            $('#searchResponse').text('Error: ' + error);

        }
    });
}

/**
 * Deletes a student by id
 */
function deleteStudent() {
    // Logic to handle search operation
    //alert('Delete button clicked');

    // 1. Get input values
    var input1 = $('#deleteInput').val();

    // 2. create ajax call
    $.ajax({
        url: '/delete_student/' + input1, // choose correct endpoint
        type: 'DELETE', // choose correct type
        success: function(response) {
            // Handle success response
            console.log('Delete operation successful');
            // show result to user
            $('#deleteResponse').text('Server Response: ' + JSON.stringify(response));
            getEverythingAsTable();
            $('#deleteInput').val('');
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error:', error);
            // show result to user
            $('#deleteResponse').text('Error: ' + error);

        }
    });
}

function resetTable(){
    $.ajax({
        url: '/reset-table', // choose correct endpoint
        type: 'DELETE', // choose correct type
        success: function(response) {
            // Handle success response
            console.log('Reset operation successful');
            // show result to user
            $('#resetResponse').text('Server Response: ' + JSON.stringify(response));
            getEverythingAsTable();
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error('Error:', error);
            // show result to user
            $('#resetResponse').text('Error: ' + error);

        }
    });
}

function searchForUpdate(){
     // 1. Get input values
     var input1 = $('#searchInput').val();

     // 2. create ajax call
     $.ajax({
         url: '/read_student_simple/' + input1, // choose correct endpoint
         type: 'GET', // choose correct type
         success: function(response) {
             // Handle success response
             console.log('Search operation successful');
             // show result to user
             $('#searchResponse').text('Server Response: ' + JSON.stringify(response));
            var count = 1;
            for(var field in response){
                $('#updateInput' + count).val(response[field]);
                count++;
            }
         },
         error: function(xhr, status, error) {
             // Handle error response
             console.error('Error:', error);
             // show result to user
             $('#searchResponse').text('Error: ' + error);
 
         }
     });
}


function getEverythingAsTable(){
    $('#studentTable').remove();
    $.ajax({
        url: "/read-table",
        type: "GET",
        dataType: "json",
        success: function(data) {
            // Create HTML for the table header row with descriptions
            var tableHtml = '<table id="studentTable" class="table_line">';
            tableHtml += '<tr>';
            $.each(data[0], function(key, _) {
                tableHtml += '<th>' + key + '</th>';
            });
            tableHtml += '</tr>';

            // Iterate over the list of StudentEntity objects to generate table rows
            $.each(data, function(_, student) {
                tableHtml += '<tr>';
                $.each(student, function(_, value) {
                    tableHtml += '<td>' + value + '</td>';
                });
                tableHtml += '</tr>';
            });

            tableHtml += '</table>';
            
            // Append the HTML for the table to the studentList container
            $('#studentList').append(tableHtml);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching data:", error);
        }
    });
}