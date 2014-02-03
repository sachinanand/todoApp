<%@ page import="com.ig.Reminder" %>



<div class="fieldcontain ${hasErrors(bean: reminderInstance, field: 'reminderDate', 'error')} required">
	<label for="reminderDate">
		<g:message code="reminder.reminderDate.label" default="Reminder Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="reminderDate" precision="day"  value="${reminderInstance?.reminderDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reminderInstance, field: 'task', 'error')} required">
	<label for="task">
		<g:message code="reminder.task.label" default="Task" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="task" name="task.id" from="${com.ig.Task.list()}" optionKey="id" required="" value="${reminderInstance?.task?.id}" class="many-to-one"/>
</div>

