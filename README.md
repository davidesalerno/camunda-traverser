# Traverse a BPMN diagram using Camunda engine
## General information 

The code in this project is developed in order to test the Camunda engine (see [documentation](https://docs.camunda.org/manual/latest/user-guide/model-api/bpmn-model-api/read-a-model/)) 

The project is using Maven to manage the dependencies and all the phases (compile, test, install, package etc).

So in order to build the JAR, if you have got Maven and a JDK on your workstation you can simply hit che command: 

```bash
mvn compile package assembly:single
```

and than you can find the traverser.jar into the target folder so that you can test it using the command:

```bash
java -jar target/traverser.jar approveInvoice invoiceProcessed
```

## What is it BPMN? 

A BPMN diagram is a type of flowchart that uses standardized icons to represent the different elements and flow of a business process. BPMN was originally developed in 2004 by the Business Process Management Initiative (BPMI).


## Project Goal

The goal of this project is to develop a Java program that does the following:

- It fetches the XML representation of the exemplary ‘invoice approval’ BPMN diagram depicted from a [GitHub Gist](https://gist.githubusercontent.com/davidesalerno/5933f80186d99285debe2cb1d359932f/raw/1c4e71e8e0443330dd94960edd53ec90e1244748/invoice_process_diagram.json).

- It parses the XML into a traversable data structure.

- It finds one possible path on the graph between a given start node and a given end node.

- It prints out the IDs of all nodes on the found path to System.out.