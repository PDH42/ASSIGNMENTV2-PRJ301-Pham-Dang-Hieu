<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<aside>
    <div>
        <nav>
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/user/mark">Mark</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/student">Student</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/instructor">Instructor</a>
                </li>
            </ul>
        </nav>
    </div>
</aside>
