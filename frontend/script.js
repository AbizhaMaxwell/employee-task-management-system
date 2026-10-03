document.getElementById("loginForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const message = document.getElementById("message");

    try {

        const response = await fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        const result = await response.text();

        message.textContent = result;

        if (result.includes("Role: ADMIN")) {
            window.location.href = "admin-dashboard.html";
        }

        if (result.includes("Role: EMPLOYEE")) {
            window.location.href = "employee-dashboard.html";
        }

    } catch (error) {

        message.textContent = "Unable to connect to server.";

    }
});