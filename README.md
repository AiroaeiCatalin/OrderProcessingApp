<!-- PROJECT LOGO -->


<h3 align="center"> SACOM order processing app </h3>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#built-with">Built with</a>
      <ul>
        <li><a href="#technology">Technology</a></li>
        <li><a href="#additional-libraries">Additional libraries</a></li>
      </ul>
    </li>
    <li><a href="#how-to-run-the-app">How to run the app</a></li>
      <ul>
        <li><a href="#getting-started">Getting started</a></li>
        <li><a href="#usage">Usage</a></li>
      </ul>
    <li><a href="#proposed-solution">Proposed solution</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

SACOM is an electronic retailer that has multiple online and physical stores.</br>
SACOM customers place product orders from various locations and systems and these orders need to be
processed and sent to the corresponding suppliers.
The orders are sent as XML files on a central server, where my application processes them and
create the XML files that will be sent to the suppliers. Following is a system overview diagram:


## Built With

### Technology
Java version 17

### Additional libraries:
JAXB for the XML marshalling and unmarshalling




<!-- How to run the app -->
## How to run the app

### Getting started
This application can be tested by opening the project through the pom.xml and installing all additional libraries from Maven, in our case the JAXB.

### Usage

After installing all the additional libraries you can just run the app and it will detect if you place a new file inside the resources/input directory and if the file 
has the right naming format it will convert it to the needed XML supplier files, one for each of the suppliers, that contain the products from that specific supplier.

Here you can see some prints from the app: </br>
<p>You can just place one or more files in the input folder inside the resources directory:</p>
<img width="203" alt="Screenshot 2022-02-17 at 14 54 02" src="https://user-images.githubusercontent.com/37214035/154486252-318a0dd8-0ea0-4f12-a495-5b754501f83c.png">

<p>The app will generate for each of the files the supplier xml's which contain all the products from that supplier:</p>
<img width="201" alt="Screenshot 2022-02-17 at 14 54 14" src="https://user-images.githubusercontent.com/37214035/154486279-be778918-40f3-4ce0-b0a5-3e35bc95c858.png">

<p>The console output:</p>
<img width="688" alt="Screenshot 2022-02-17 at 14 54 36" src="https://user-images.githubusercontent.com/37214035/154486301-df0ddaa9-8e39-4207-8953-f900f23ab250.png">



<!-- Proposed solution -->
## Proposed solution
The solution I aproached was firstly to watch the input directory where we drop the files, I have done this 
with the help of the WatchService. After we detect a new file we then check if the naming follows the right pattern(i.e: orders##.xml), and if it does we then extract the order's number from 
the file's name and pass it toghether with the file path to the reader where we unmarshall the xml file with the help of the JAXB library and create the objects for the order and the product and then we manage them,
we sort them and we create a HashMap with the key being the supplier and the value
a list with the objects from that supplier and pass this hashmap and the order number to the writer where we just marshall the objects and create the xml in the output folder.
</br>
</br>
In the main class we instatiate all the objects and inject them where needed.
</br>
</br>
We have 2 interfaces for the reader and the writer so that if needed the read and write methods can have different implementations. 






