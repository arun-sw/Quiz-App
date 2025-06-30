const questionBase = "http://localhost:8080/question"

async function addQuestion() {

    const question = {
        questionTitle: document.getElementById("questionText").value,
        category: document.getElementById("questionCategory").value,
        option1: document.getElementById("option1").value,
        option2: document.getElementById("option2").value,
        option3: document.getElementById("option3").value,
        option4: document.getElementById("option4").value,
        rightAnswer: document.getElementById("correctAnswer").value
    };
    if (!question.questionTitle || !question.category || !question.rightAnswer) {
        document.getElementById("addQuestionResult").style.color="#b52a37"
        document.getElementById("addQuestionResult").textContent = "Please fill in all required fields.";
        return;
    }
    const res = await fetch(`${questionBase}/add`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(question)
    });

    document.getElementById("addQuestionResult").textContent = await res.text();
}

//view all the question


async function loadAllquiz() {
    const list1 = document.getElementById("allquestiontable")

    try {
        const res = await fetch(`${questionBase}/allquiz`);
        const data = await res.json();
        const list = document.getElementById("innertable");
        console.log(data)

        if (data.length === 0) {
            alert("No question is available ")
            list1.style.display = "none";
        } else {
            list1.style.display = "block"
        }
        list.innerHTML = "";
        data.forEach(q => {
            const row = `<tr>
                                    <td>${q.id}</td>
                                    <td>${q.category}</td>
                                    <td>${q.questionTitle}</td>
                                    <td>${q.option1}</td>
                                    <td>${q.option2}</td>
                                    <td>${q.option3}</td>
                                    <td>${q.option4}</td>
                                    <td>${q.rightAnswer}</td>
                                  </tr>`
            list.innerHTML += row;
        });
    } catch (error) {
        console.error("Error fetching questions:", error);
        alert("Failed to fetch questions. Please try again.");
    }
}
// view the quiz and answer
async function loadAllQuestions() {

    const res = await fetch(`${questionBase}/QA`);
    const data = await res.json();
    const list = document.getElementById("questionTable");
    const table=document.getElementById("allQuestions")
    console.log(data)
    table.innerHTML = "";
    if (data.length === 0) {
        alert("No question is available ")
        list.style.display = "none";
    } else {
        list.style.display = "block"
    }
    data.forEach(q => {
        const row = `<tr>
                                <td>${q.id}</td>
                                <td>${q.questionTitle}</td>
                                <td>${q.rightAnswer}</td>
                                <td>${q.category}</td>
                             </tr>`;
        table.innerHTML += row;
    });
    console.log(data)
    list.style.display="block"
    document.getElementById("btt").style.display="none"
}

//delete
async function deleted() {
    const id=document.getElementById("id").value;

    try {
        const res = await fetch(`${questionBase}/delete/${id}`, {
            method: "DELETE",

        });
        document.getElementById("result").textContent = await res.text();
    }
    catch(error){
        console.error("Deleting Error"+error);
        document.getElementById("result").style.color= "#b52a37"
        document.getElementById("result").textContent = "Error deleteing the question"
    }
}
// update quiz
function loadQuiz() {
    const id = document.getElementById("IDForUp").value;
    if (id===0||!id){
        alert("Data not valid")
    }

    fetch(`http://localhost:8080/question/edit/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Question not found");
            return response.json();
        })
        .then(data => {
            document.getElementById("questionTextup").value = data.questionTitle;
            document.getElementById("option1up").value = data.option1;
            document.getElementById("option2up").value = data.option2;
            document.getElementById("option3up").value = data.option3;
            document.getElementById("option4up").value = data.option4;
            document.getElementById("correctAnswerup").value = data.rightAnswer;
        })
        .catch(error => {
            alert("Error: " + error.message);
        });
}

// update the question
async function updateQuiz() {
    const id = document.getElementById("IDForUp").value;

    const payload = {
        questionTitle: document.getElementById("questionTextup").value,
        option1: document.getElementById("option1up").value,
        option2: document.getElementById("option2up").value,
        option3: document.getElementById("option3up").value,
        option4: document.getElementById("option4up").value,
        rightAnswer: document.getElementById("correctAnswerup").value
    };

    try {
        const res = await fetch(`http://localhost:8080/question/update/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        const data = await res.json();

        if (res.ok) {
            document.getElementById("delete").style.color = "#28a745";
        } else {
            document.getElementById("delete").style.color = "#b52a37";
        }

        document.getElementById("delete").textContent = data.message;

    } catch (error) {
        document.getElementById("delete").style.color = "#b52a37";
        document.getElementById("delete").textContent = "Error: Could not connect to the server.";
        console.error("Update error:", error);
    }
}

// show by category
async function ShowByCategory() {
    const selected = document.getElementById("category").value;
    const list1=document.getElementById("questionTable1")

    console.log(selected);
    if (selected === "none") {
        alert("Please select a category.");
        return;

    }
    try {
        const res = await fetch(`http://localhost:8080/question/category/${encodeURIComponent(selected)}`);
        const data = await res.json();
        const list = document.getElementById("innerTable1");


        console.log(data)
        if(data.length === 0){
            alert("No question is available in this category")
            list1.style.display="none";
        }
        else {
            list1.style.display = "block"
        }
        list.innerHTML = "";
        data.forEach(q => {
            const row = `<tr>
                                    <td>${q.id}</td>
                                    <td>${q.category}</td>
                                    <td>${q.questionTitle}</td>
                                    <td>${q.option1}</td>
                                    <td>${q.option2}</td>
                                    <td>${q.option3}</td>
                                    <td>${q.option4}</td>
                                    <td>${q.rightAnswer}</td>
                                  </tr>`
            list.innerHTML += row;
        });

    } catch (error) {
        console.error("Error fetching questions:", error);
        alert("Failed to fetch questions. Please try again.");
    }
}
// category
window.onload = function () {
    fetch("http://localhost:8080/question/categories") // Your Spring Boot endpoint
        .then(response => response.json())
        .then(data => {
            const dropdown = document.getElementById("category");
            data.forEach(category => {
                const option = document.createElement("option");
                option.value = category;
                option.text = category;
                dropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error fetching categories:", error);
        });
};



// get by id
async function loadquiz() {
    const list = document.getElementById("questionTable2");


    const id=document.getElementById("quizId").value
    if(id === "none" ||id.trim() === ""){
        alert("Please enter a valid ID");
        return;
    }

    try {
        const res = await fetch(`http://localhost:8080/question/get/${id}`);
        if (!res.ok) {
            const text = await res.text();
            throw new Error(`HTTP error! status: ${res.status}`);
        }
        const q = await res.json();
        if(q === "none") {
            list.style.display = "none";
            alert("No question is available in this id")
        }
        else {
            list.style.display = "block"
        }
        const table = document.getElementById("innertable2");

        table.innerHTML = "";
        const row = `<tr>
                                    <td>${q.id}</td>
                                    <td>${q.category}</td>
                                    <td>${q.questionTitle}</td>
                                    <td>${q.option1}</td>
                                    <td>${q.option2}</td>
                                    <td>${q.option3}</td>
                                    <td>${q.option4}</td>
                                    <td>${q.rightAnswer}</td>
                                  </tr>`
        table.innerHTML += row;
        document.getElementById("questionTable2").style.display="block";
        document.querySelector(".container2").style.display = "none";
    } catch(error)
    {
        alert("Failed to fetch question: ");
    }
}
