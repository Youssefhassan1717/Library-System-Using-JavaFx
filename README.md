<!DOCTYPE html>
<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 40px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 4px;
    }

    .logo {
      text-align: center;
      margin-bottom: 20px;
    }

    h1 {
      font-size: 28px;
      color: #333;
      margin-bottom: 20px;
    }

    p {
      font-size: 16px;
      color: #555;
      margin-bottom: 20px;
    }

    a {
      color: #007bff;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }

    .badge {
      display: inline-block;
      padding: 5px 10px;
      font-size: 14px;
      font-weight: bold;
      color: #fff;
      background-color: #007bff;
      border-radius: 4px;
    }

    .table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .table th,
    .table td {
      padding: 8px;
      border-bottom: 1px solid #ddd;
      text-align: left;
    }

    .table th {
      font-weight: bold;
    }

    .code {
      background-color: #f5f5f5;
      padding: 10px;
      border-radius: 4px;
      margin-bottom: 20px;
      font-family: Consolas, monospace;
    }

    .code span {
      color: #555;
    }

    .button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #007bff;
      color: #fff;
      text-decoration: none;
      border-radius: 4px;
      transition: background-color 0.3s;
    }

    .button:hover {
      background-color: #0056b3;
    }

    .center {
      text-align: center;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="library_system_logo.png" alt="Library System Logo">
    </div>

    <h1>Library System</h1>

    <p>
      <span class="badge">License</span>
      <a href="https://github.com/your-username/library-system/LICENSE">MIT</a>
    </p>
    <p>
      <span class="badge">YouTube</span>
      <a href="https://www.youtube.com/watch?v=aaieuB-zHxA&t=10s">Video</a>
    </p>

    <blockquote>
      <p>A Java-based Library Management System</p>
    </blockquote>

    <h2>Table of Contents</h2>

    <ul>
      <li><a href="#introduction">Introduction</a></li>
      <li><a href="#features">Features</a></li>
      <li><a href="#installation">Installation</a></li>
      <li><a href="#usage">Usage</a></li>
      <li><a href="#contributing">Contributing</a></li>
      <li><a href="#license">License</a></li>
    </ul>

    <h2 id="introduction">Introduction</h2>

    <p>
      This Library System is a Java-based application designed to manage library operations such as adding, updating,
      and deleting books, managing user information, and handling book borrowing and returning.
    </p>

    <h2 id="features">Features</h2>

    <ul>
      <li>Add and manage books</li>
      <li>Manage user accounts</li>
      <li>Handle book borrowing and returning</li>
      <li>Generate reports</li>
    </ul>

    <h2 id="installation">Installation</h2>

    <p>To install the Library System, follow these steps:</p>

    <div class="code">
      <span># Clone the repository</span><br>
      git clone https://github.com/your-username/library-system.git<br><br>

      <span># Change to project directory</span><br>
      cd library-system<br><br>

      <span># Build the project</span><br>
      mvn clean install<br><br>

      <span># Run the application</span><br>
      java -jar target/library-system.jar
    </div>

    <h2 id="usage">Usage</h2>

    <p>
      Once the application is up and running, you can access it through your web browser by navigating to
      <a href="http://localhost:8080">http://localhost:8080</a>.
    </p>

    <h2 id="contributing">Contributing</h2>

    <p>
      Contributions are welcome! If you find any issues or would like to suggest improvements, please create a new
      <a href="https://github.com/your-username/library-system/issues">issue</a> or submit a pull request.
    </p>

    <h2 id="license">License</h2>

    <p>This project is licensed under the <a href="https://github.com/your-username/library-system/LICENSE">MIT License</a>.</p>

    <div class="center">
      <a class="button" href="https://github.com/your-username/library-system">View on GitHub</a>
    </div>
  </div>
</body>
</html>
