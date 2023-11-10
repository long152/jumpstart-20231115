package com.greenfossil.jumpstart.day2

import munit.FunSuite

class TaskListSuite extends FunSuite:

  override def beforeAll(): Unit =
    TaskList.addTasks("Task 1", "Task 2", "Task 3")

  test("Complete task"):
    TaskList.completeTask(task => task.id == 1)
    assert(TaskList.findTask(_.id == 1).get.isCompleted)

  test("Delete task"):
    TaskList.deleteTask(task => task.id == 2)
    assert(TaskList.findTask(_.id == 2).isEmpty)
