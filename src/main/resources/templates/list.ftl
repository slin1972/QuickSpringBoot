<!DOCTYPE html>

<html lang="en">

<body>
	<table>
		<thead>
			<tr>
				<td>id</td>
				<td>username</td>
				<td>age</td>
			</tr>
		</thead>
		<tbody>
<#list users as user>
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.age}</td>
		    </tr>
</#list>
		</tbody>
	</table>
</body>
</html>