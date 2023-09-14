document.addEventListener("DOMContentLoaded", function() {
    const teamMembersSelect = document.getElementById("teamMembers");
    const addMemberButton = document.getElementById("addMember");
    const selectedMembersSection = document.getElementById("selectedMembersSection");

    addMemberButton.addEventListener("click", function() {
        const selectedMembers = [];
        const selectedOptions = Array.from(teamMembersSelect.selectedOptions);

        selectedOptions.forEach(function(option) {
            selectedMembers.push(option.value);
        });

        selectedMembers.forEach(function(member) {
            const memberElement = document.createElement("div");
            memberElement.textContent = member;
            selectedMembersSection.appendChild(memberElement);
         
        // Remove the selected member from the dropdown
         teamMembersSelect.remove(teamMembersSelect.selectedIndex);
        });
    });
});

