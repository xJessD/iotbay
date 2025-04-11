<html>
    <% 
        // Variables go here
    %>

    <header>
        <title>Log Out</title>
<<<<<<< HEAD
<<<<<<< HEAD
        <link rel="stylesheet" href="css.css">
=======
        <link href="/webapp/css.css" rel="stylesheet" type="text/css" >
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
        <link rel="stylesheet" href="css.css">
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
         
    </header>

    <body>
            <%@ include file="header.jsp" %><!-- Site title and nav please do not remove -->

        
        <main><!-- Please put all content inside the main tags -->
            <section>
<<<<<<< HEAD
<<<<<<< HEAD
                <%session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="login.jsp">home</a>.</p>
=======
                <% session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="/webapp/index.jsp">home</a>.</p>
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
                <%session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="login.jsp">home</a>.</p>
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
            </section>
                
        </main>
        

            <%@ include file="footer.jsp" %><!-- Site footer please do not remove -->
    </body>
</html>
