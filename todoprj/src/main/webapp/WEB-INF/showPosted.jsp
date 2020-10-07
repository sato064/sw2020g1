<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Student" %>
<%@ page import="model.Project" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Comment" %>
<%List<Project> projectList = (ArrayList<Project>) request.getAttribute("projectList"); %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-grid.css">

<%Student student = (Student) session.getAttribute("student"); %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>home</title>
    <script type="text/javascript">
        function confirmDelete(){
            flag = confirm("本当に削除しますか？");
            // 「はい」が押されたときの処理
            if ( flag == true ){
                alert("削除しました");
                return true;
            }else{    // 「いいえ」が押されたときの処理
                return false;
            }
        };
    </script>
</head>
<body>
<header>
    <jsp:include page="uiKit.html"/>
</header>
<div class="uk-container" style="height: 70px">

</div>
<div class="uk-container uk-container-center uk-margin">
    <div class="uk-text-center">
        <%if (projectList.size() == 0) {%>
        プロジェクトがありません
        <%} else {%>
    </div>
    <div class="uk-text-center">
        <span uk-icon="user"></span><%=student.getStudentName()%>さん
    </div>
    <br>
    <%int i = 0;%>
    <%for (Project project : projectList) {%>
    <%project = projectList.get(i);%>
    <%if (student.getStudentID().equals(project.getAuthorID(""))){%>
    <div class="uk-card uk-card-body uk-card-default uk-card-hover" style="background-color: #ffffdd">
        <div class="uk-card-header">
            <div class="uk-grid-small uk-flex-middle" uk-grid>
                <div class="uk-width-expand">
                    <h3 class="uk-card-title uk-margin-remove-bottom">プロジェクト名：<%=project.getProjectNAME("projectNAME")%></h3>
                    <p class="uk-text-meta uk-margin-remove-top"><address>説明：<%=project.getProjectINFO("projectINFO")%></address></p>
                </div>
                <div class="uk-card-badge uk-label">
                    <span uk-icon="user"></span><%=project.getAuthorNAME("authorNAME")%>
                </div>
                <form action="/TodoPrj/DeleteProject" method="post">
                    <input type="hidden" name="projectId" value=<%=project.getProjectID(1)%>>
                    <button type="submit" class="uk-icon-button" uk-icon="trash" onclick="return confirmDelete()"></button>
                </form>
                <button type="button" class="uk-icon-button" uk-icon="commenting" uk-toggle="target: #comment-form1"></button>
                <form action="/TodoPrj/DisplayComment" method="post">
                    <input type="hidden" name="projectId" value=<%=project.getProjectID(1)%>>
                    <input type="hidden" name="projectName" value=<%=project.getProjectNAME("projectNAME")%>>
                    <input type="hidden" name="projectInfo" value=<%=project.getProjectINFO("projectINFO")%>>
                    <input type="hidden" name="authorName" value=<%=project.getAuthorNAME("authorNAME")%>>
                    <input type="hidden" name="detailedInfo" value=<%=project.getDetailedINFO("detailedInfo")%>>
                    <button class="uk-icon-button" type="submit" uk-icon="comments"></button>
                </form>
                <div id="comment-form1" uk-modal style="z-index: 9999">
                    <div class="uk-modal-dialog uk-modal-body">
                        <h2 class="uk-modal-title">コメント記入</h2>
                        <form action="/TodoPrj/RegistComment" method="post">
                            <textarea class="uk-textarea" rows="4" type="commentDetail" name="commentDetail" placeholder="コメント" maxlength="200" size="60" pattern=".*\S+.*"></textarea>
                            <input type="hidden" name="commentAuthorId" value=<%=student.getStudentID()%>>
                            <input type="hidden" name="projectId" value=<%=project.getProjectID(1)%>>
                            <div class="uk-text-right">
                                <button class="uk-button uk-button-primary" type="submit">Save</button>
                            </div>
                        </form>
                        <div class="uk-text-left">
                            <button class="uk-button uk-button-default uk-modal-close" type="button">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="uk-card-body">
            <p><%=project.getDetailedINFO("detailedINFO")%></p>
        </div>
    </div>
    <%} else {%>
    <div class="uk-card uk-card-body uk-card-default uk-card-hover" style="background-color: #ffffdd">
        <div class="uk-card-header">
            <div class="uk-grid-small uk-flex-middle" uk-grid>
                <div class="uk-width-expand">
                    <h3 class="uk-card-title uk-margin-remove-bottom">プロジェクト名：<%=project.getProjectNAME("projectNAME")%></h3>
                    <p class="uk-text-meta uk-margin-remove-top"><address>説明：<%=project.getProjectINFO("projectINFO")%></address></p>
                </div>
                <div class="uk-card-badge uk-label">
                    <span uk-icon="user"></span><%=project.getAuthorNAME("authorNAME")%>
                </div>
                <button type="submit" class="uk-icon-button" uk-icon="commenting" uk-toggle="target: #comment-form2"></button>
                <form action="/TodoPrj/DisplayComment" method="post">
                    <input type="hidden" name="projectId" value=<%=project.getProjectID(1)%>>
                    <input type="hidden" name="projectName" value=<%=project.getProjectNAME("projectNAME")%>>
                    <input type="hidden" name="projectInfo" value=<%=project.getProjectINFO("projectINFO")%>>
                    <input type="hidden" name="authorName" value=<%=project.getAuthorNAME("authorNAME")%>>
                    <input type="hidden" name="detailedInfo" value=<%=project.getDetailedINFO("detailedInfo")%>>
                    <button class="uk-icon-button" type="submit" uk-icon="comments"></button>
                </form>
                <div id="comment-form2" uk-modal style="z-index: 9999">
                    <div class="uk-modal-dialog uk-modal-body">
                        <h2 class="uk-modal-title" >コメント記入</h2>
                        <form action="/TodoPrj/RegistComment" method="post">
                            <textarea class="uk-textarea" rows="4" type="commentDetail" name="commentDetail" placeholder="コメント" maxlength="200" size="60" pattern=".*\S+.*"></textarea>
                            <input type="hidden" name="commentAuthorId" value=<%=student.getStudentID()%>>
                            <input type="hidden" name="projectId" value=<%=project.getProjectID(2)%>>
                            <div class="uk-text-right">
                                <button type="submit" href="/TodoPrj/RegistComment" class="uk-button uk-button-primary">Save</button>
                            </div>
                        </form>
                        <div class="uk-text-left">
                            <button class="uk-button uk-button-default uk-modal-close" type="button">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="uk-card-body">
            <p><%=project.getDetailedINFO("detailedINFO")%></p>
        </div>
    </div>
    <%}%>
    <br>
    <%i++;%>
    <%}%>
    <%}%>
</div>
</body>
</html>
