<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="mainNav">
    <ul>
        <li><a href="inote?command=show"><i class="icon-home"></i><br />${local.getValue("menu.home")}</a></li>
        <li><a href="inote?command=create"><i class="icon-user"></i><br />${local.getValue("menu.add")}</a></li>
        <li><a href="inote?command=group"><i class="icon-cog"></i><br />${local.getValue("menu.group")}</a></li>
        <li><a href="inote?command=birthday"><i class="icon-twitter"></i><br />${local.getValue("menu.birthday")}</a></li>
        <li><a href="inote?command=logout"><br />${local.getValue("logout")}</a></li>
    </ul><div class="clr"></div>
</nav>
