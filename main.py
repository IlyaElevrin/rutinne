from flask import Flask, render_template, request, redirect, url_for, jsonify
import json
import os

# Initialize Flask app
app = Flask(__name__)

# File for storing tasks
data_file = "tasks.json"

# Load tasks from file
def load_tasks():
    if not os.path.exists(data_file):
        return []
    with open(data_file, "r", encoding="utf-8") as f:
        return json.load(f)

# Save tasks to file
def save_tasks(tasks):
    with open(data_file, "w", encoding="utf-8") as f:
        json.dump(tasks, f, ensure_ascii=False, indent=4)

# Route for the main page
@app.route("/")
def index():
    tasks = load_tasks()
    return render_template("index.html", tasks=tasks)

# Route to add a new task
@app.route("/add", methods=["POST"])
def add_task():
    tasks = load_tasks()
    task_name = request.form.get("task")
    if task_name:
        tasks.append({"name": task_name, "done": False})
        save_tasks(tasks)
    return redirect(url_for("index"))

# Route to toggle task status
@app.route("/toggle/<int:task_id>")
def toggle_task(task_id):
    tasks = load_tasks()
    if 0 <= task_id < len(tasks):
        tasks[task_id]["done"] = not tasks[task_id]["done"]
        save_tasks(tasks)
    return redirect(url_for("index"))

# Route to delete a task
@app.route("/delete/<int:task_id>")
def delete_task(task_id):
    tasks = load_tasks()
    if 0 <= task_id < len(tasks):
        tasks.pop(task_id)
        save_tasks(tasks)
    return redirect(url_for("index"))

if __name__ == "__main__":
    app.run(debug=True)
