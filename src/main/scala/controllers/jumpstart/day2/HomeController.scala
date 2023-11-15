package controllers.jumpstart.day2

import com.greenfossil.data.mapping.Mapping
import com.greenfossil.data.mapping.Mapping.*
import com.greenfossil.jumpstart.day2.TaskList
import com.greenfossil.thorium.{*, given}
import com.linecorp.armeria.server.annotation.*
import views.jumpstart.day2.IndexPage

object HomeController:

  private val addTaskMapping: Mapping[String] = nonEmptyText.name("task")

  @Get("/")
  def index =
    val tasks = TaskList.findTasks(_ => true)
    Ok(IndexPage(tasks, addTaskMapping).render)

  @Post("/task/create")
  def createTask = Action{ implicit request =>
    val tasks = TaskList.findTasks(_ => true)
    addTaskMapping.bindFromRequest().fold(
      errorForm => BadRequest(IndexPage(tasks, errorForm).render),
      newTask =>
        TaskList.addTasks(newTask)
        Redirect(HomeController.index)
    )
  }

  /**
   * Implement controller action that deletes the task with ID, and redirect to the index page.
   * Expectation: The matching task should no longer be in the list
   */
  @Post("/task/delete/:taskId")
  def deleteTask(@Param taskId: Long) = Action{ implicit request =>
    TaskList.deleteTask(_.id == taskId)
    Redirect(HomeController.index)
  }

  /**
   * Implement controller action that completes the task with ID, and redirect to the index page.
   * Expectation: The matching task should be marked as completed
   */
  @Post("/task/complete/:taskId")
  def completeTask(@Param taskId: Long) = Action{ implicit request =>
    TaskList.completeTask(_.id == taskId)
    Redirect(HomeController.index)
  }

