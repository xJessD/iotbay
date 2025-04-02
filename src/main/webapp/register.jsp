<html>
    <header>
        <title>Register</title>
        <link href="/webapp/css.css" rel="stylesheet" type="text/css" >
        <% 
        // Variables go here
        %> 
    </header>

    <body>
            <%@ include file="header.jsp" %><!-- Site title and nav please do not remove -->

        
        <main><!-- Please put all content inside the main tags -->
            <section>
                <h2>Register</h2>

                <form action="/webapp/index.jsp" method="post">
                
                <label for="fname">First Name: </label>
                <input type="text" name="fname" />

                <label for="lname">Last Name: </label>
                <input type="text" name="lname" />

                <label for="email">Email: </label>
                <input type="text" name="email">

                <label for="password">Password: </label>
                <input type="password" name="password" />

                
                <label for="TOS">TOS: </label>
                <input type="checkbox" name="TOS" />

                <br>

                <input type="submit" class="submit-btn" />
            </form>
            </section>
                
        </main>
        

            <%@ include file="footer.jsp" %><!-- Site footer please do not remove -->
    </body>
</html>
