<html>
    <% 
        // Variables go here
    %>

    <header>
        <title>Log Out</title>
<<<<<<< HEAD
        <link rel="stylesheet" href="css.css">
=======
        <link href="/webapp/css.css" rel="stylesheet" type="text/css" >
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
         
    </header>

    <body>
            <%@ include file="header.jsp" %><!-- Site title and nav please do not remove -->

        
        <main><!-- Please put all content inside the main tags -->
            <section>
<<<<<<< HEAD
                <%session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="login.jsp">home</a>.</p>
=======
                <% session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="/webapp/index.jsp">home</a>.</p>
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
            </section>
                
        </main>
        

            <%@ include file="footer.jsp" %><!-- Site footer please do not remove -->
    </body>
</html>
