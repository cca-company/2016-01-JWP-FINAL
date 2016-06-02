<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Issue Tracking System</title>

    <link href='https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue_grey-orange.min.css">
    <link rel="stylesheet" href="/static/styles.css">
  </head>
  <body>
    <div class="issues issues--wrap mdl-layout mdl-js-layout has-drawer is-upgraded">

    <header class="mdl-layout__header mdl-layout__header--waterfall">
      <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">Issue Tracking System</span>
        <div class="mdl-layout-spacer"></div>
        <!-- Tabs -->
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="/">Issues</a>
          <a class="mdl-navigation__link" href="/milestones">Milestones</a>
          <c:choose>
            <c:when test="${sessionScope.user ne null}">
              <a class="mdl-navigation__link" href="/logout">logout</a>
            </c:when>
            <c:otherwise>
              <a class="mdl-navigation__link" href="/login">login</a>
              <a class="mdl-navigation__link" href="/join">join</a>
            </c:otherwise>
          </c:choose>
        </nav>
      </div>
    </header>

      <main class="mdl-layout__content">
        <div class="issues-back">
          <a href="/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon back-btn" title="go back" role="button">
            <i class="material-icons" role="presentation">arrow_back</i>
          </a>
        </div>
        <div class="issues__posts mdl-grid">
          <div class="mdl-card mdl-shadow--4dp mdl-cell mdl-cell--12-col">
            <div class="mdl-card__menu">
              <a href="/updateIssue/${issue.id}" class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">edit</i>
              </a>
              <a href="/deleteIssue/${issue.id}" id="issues-menu-lower-right" class="mdl-button mdl-js-button mdl-button--icon">
                <i class="material-icons">delete</i>
              </a>
            </div>
            <div class="mdl-card__title mdl-color-text--grey-50">
              <i class="material-icons">check_circle</i>
              <h2 class="mdl-card__title-text">${issue.subject} #${issue.id}</h2>
            </div>
            
            <div class="mdl-color-text--grey-700 mdl-card__supporting-text meta">
              <img class="minilogo" height="48" width="48" src="${writer.pic}" alt="${writer.name}">
              <div style="margin-right:20px;">
                <div class="author-text">
                  <strong>
                    <a href="https://github.com/javajigi" class="author">${writer.name}</a>
                  </strong>
                </div>
                <span>${issue.date}</span>
              </div>

              <a href="/issue/${issue.id}/setOpen" class="mdl-button mdl-button--colored mdl-js-button">
                <c:choose>
                  <c:when test="${issue.isOpen}">
                      Close issue
                  </c:when>
                  <c:otherwise>
                      Reopen issue
                  </c:otherwise>
                </c:choose>
              </a>

              <div class="section-spacer"></div>

              <button id="milestone-menu" class="mdl-button mdl-js-button">
                Milestone
              </button>
              <!-- milestone list -->
              <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect"
                  for="milestone-menu">
                <c:forEach items="${milestones}"  var="milestone">
                  <c:choose>
                    <c:when test="${issue.milestoneId eq milestone.id}">
                      <li class="mdl-menu__item mdl-button--accent">
                        <a href="/issue/${issue.id}/setMilestone/0">${milestone.subject}</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="mdl-menu__item">
                        <a href="/issue/${issue.id}/setMilestone/${milestone.id}">${milestone.subject}</a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </ul>

              <button id="label-menu" class="mdl-button mdl-js-button">
                Label
              </button>
              <!-- label list -->
              <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect"
                  for="label-menu">
                <c:forEach items="${labels}"  var="label">
                  <c:choose>
                    <c:when test="${issue.labelId eq label.id}">
                      <li class="mdl-menu__item mdl-button--accent">
                        <a href="/issue/${issue.id}/setLabel/0">${label.labelText}</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="mdl-menu__item">
                        <a href="/issue/${issue.id}/setLabel/${label.id}">${label.labelText}</a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </ul>

              <button id="assignee-menu" class="mdl-button mdl-js-button">
                Assignee
              </button>
              <!-- label list -->
              <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect"
                  for="assignee-menu">
                <c:forEach items="${users}"  var="user">
                  <c:choose>
                    <c:when test="${issue.assigneeId eq user.id}">
                      <li class="mdl-menu__item mdl-button--accent">
                        <a href="/issue/${issue.id}/setAssignee/0">${user.name}</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="mdl-menu__item">
                        <a href="/issue/${issue.id}/setAssignee/${user.id}">${user.name}</a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </ul>
            </div>
            <div class="mdl-color-text--grey-700 mdl-card__supporting-text">
              <p>${issue.comment}</p>
            </div>
            <div class="mdl-color-text--primary-contrast mdl-card__supporting-text comments">

              <!-- comments start -->
              
              <c:forEach items="${histories}"  var="history">
              
              <div class="comment mdl-color-text--grey-700">
                <header class="comment__header">
                  <img class="comment__avatar2" height="48" width="48" src="${history.writerPic}" alt="${history.writerName}">
                  <div class="comment__author">
                    <strong>
                      <a>${history.writerName}</a>

                      <c:choose>
                        <c:when test="${history.type eq 'file'}">
                          upload file <a href="${history.content}" target="_blank">[view]</a>
                        </c:when>
                        <c:otherwise>
                          <span>${history.content} on ${history.date}</span>
                        </c:otherwise>
                      </c:choose>
                    </strong>
                  </div>
                </header>
              </div>
              <hr>
              
              </c:forEach>
              <!-- comments end -->
            </div>
            <form action="/issue/${issue.id}/uploadFile" enctype="multipart/form-data" method="POST">
              <div style="margin:10px;">
                <input type="file" name="file" id="file" />
                <button class="mdl-button" type="submit">upload file</button>
              </div>
            </form>
            <div class="mdl-color-text--primary-contrast mdl-card__supporting-text new-comment">
              <form action="/issue/${issue.id}/addComment" method="POST">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <textarea rows=5 class="mdl-textfield__input" id="comment" name="comment"></textarea>
                  <label for="comment" class="mdl-textfield__label">Leave a comment</label>
                </div>
                <button type="submit" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                  <i class="material-icons" role="presentation">check</i><span class="visuallyhidden">add comment</span>
                </button>
            </form>
            </div>
          </div>
        </div>
        <footer class="mdl-mini-footer">
          <div class="mdl-mini-footer--left-section">
            <div class="mdl-logo mdl-color-text--grey-600">Proudly powered by <a href="https://github.com/Byeol">Byeol</a></div>
          </div>
        </footer>
      </main>
      <div class="mdl-layout__obfuscator"></div>
    </div>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
  </body>
</html>
