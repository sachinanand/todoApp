<%@ page import="com.ig.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="task.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${taskInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="task.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${taskInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'dueDate', 'error')} ">
	<label for="dueDate">
		<g:message code="task.dueDate.label" default="Due Date" />
		
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${taskInstance?.dueDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'reminders', 'error')} ">
	<label for="reminders">
		<g:message code="task.reminders.label" default="Reminders" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${taskInstance?.reminders?}" var="r">
    <li><g:link controller="reminder" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reminder" action="create" params="['task.id': taskInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reminder.label', default: 'Reminder')])}</g:link>
</li>
</ul>

</div>

%{--<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="task.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.ig.User.list()}" optionKey="id" required="" value="${taskInstance?.user?.id}" class="many-to-one"/>
</div>--}%

<input type = "hidden" value="${currentUserId}" name="user.id" />

