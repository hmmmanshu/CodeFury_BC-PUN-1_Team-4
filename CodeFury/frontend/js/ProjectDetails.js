function closeBug(button) {
    alert("Close the bug ? Confirm!");
    var cell = button.parentNode; // Get the cell containing the button
    cell.removeChild(button); // Remove the button
    cell.textContent = "Closed"; // Set the cell content to "Closed"
}

function assignDeveloper(button) {
    // Get the parent table cell
    const cell = button.parentElement;
    // Get the selected developer from the dropdown
    const developerSelect = cell.querySelector('.developer-list');
    const selectedDeveloper = developerSelect.value;
    // Remove the select dropdown and "Assign" button
    cell.removeChild(developerSelect);
    cell.removeChild(button);
    // Set the cell content to the selected developer
    cell.textContent = selectedDeveloper;
}