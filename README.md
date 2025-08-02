# SistemasDeVuelos_EDA - Colombian Flight Management System

## **Project Overview**
This is a sophisticated **Java-based flight management system** developed as a final project for the **Data Structures and Algorithm Analysis** course. The application simulates a Colombian airline system that uses **graph theory and classic algorithms** to solve real-world flight management problems.

## **Main Context & Concept**
The system models Colombia's domestic flight network connecting the 10 major cities:
- **Bucaramanga**, **Bogotá**, **Medellín**, **Cali**, **Barranquilla**, **Armenia**, **San Andrés**, **Valledupar**, **Arauca**, and **Ibagué**

The application represents flights as a **weighted graph** where:
- **Vertices** = Cities
- **Edges** = Available flight routes
- **Weights** = Flight prices and travel times

## **Technologies Used**

### **Core Technologies:**
- **Java SE** - Primary programming language
- **Java Swing** - GUI framework for the desktop application
- **NetBeans IDE** - Development environment (evident from `.form` files and project structure)
- **Apache Ant** - Build automation tool (`build.xml`)

### **Data Structures & Algorithms:**
- **Adjacency Matrix** - Graph representation
- **Dijkstra's Algorithm** - Shortest path calculation
- **Prim's Algorithm** - Minimum Spanning Tree for tour optimization
- **Weighted Graphs** - Multi-dimensional graph modeling

## **Development Structure**

### **Package Organization:**
```
src/
├── Proyecto/
│   ├── Grafo_MatrizAdyacencia.java    # Core graph implementation
│   └── Main.java                      # Application entry point
└── Vista/
    ├── VuelosGUI.java                 # Main GUI controller
    ├── VuelosGUI.form                 # NetBeans form design
    └── [icons and images]             # UI resources
```

### **Architecture Highlights:**
- **MVC Pattern**: Clear separation between data model (`Proyecto`) and view (`Vista`)
- **Object-Oriented Design**: Well-structured classes with proper encapsulation
- **Event-Driven GUI**: Responsive Swing interface with tabbed panels

## **Outstanding Features**

### **1. Advanced Graph Algorithms Implementation:**
- **Dijkstra's Algorithm**: Finds cheapest routes between cities
- **Prim's Algorithm**: Calculates optimal city tours with minimum cost
- **Dynamic Weight Management**: Supports both price and time optimization

### **2. Real-World Simulation Capabilities:**
- **Weather Impact**: Rain simulation that dynamically increases flight prices (2% probability per route)
- **Natural Disasters**: City isolation simulation for emergency scenarios
- **Dynamic Pricing**: Discount system with real-time price updates
- **Route Availability**: Flight cancellation and restoration features

### **3. Sophisticated User Interface:**
- **Interactive Map**: Visual representation of Colombian cities
- **Multi-Tab Interface**: Organized by functionality (disasters, weather, offers, routing)
- **Real-Time Updates**: Live flight table with current prices and times
- **Visual Feedback**: Icons and notifications for system events

### **4. Dual Optimization System:**
- **Cost Optimization**: Find cheapest flight paths
- **Time Optimization**: Find fastest routes
- **Tour Planning**: Complete city tour with minimum spanning tree

### **5. Robust Data Management:**
- **Matrix Backup System**: Original price restoration after promotions
- **State Management**: Proper handling of temporary changes
- **Error Handling**: Validation for unavailable routes and cities

## **Project Strengths**

1. **Academic Excellence**: Demonstrates mastery of fundamental CS concepts
2. **Practical Application**: Solves real-world airline management problems
3. **Algorithm Integration**: Seamless combination of multiple graph algorithms
4. **User Experience**: Intuitive interface with visual Colombian map
5. **Scalability**: Modular design allows easy addition of new cities/features
6. **Documentation**: Well-commented code with Spanish documentation
7. **Professional Structure**: NetBeans project with proper build configuration

## **Technical Innovation**
The project excellently combines theoretical computer science concepts with practical application development, showcasing advanced understanding of:
- Graph theory implementation
- Algorithm complexity analysis
- GUI programming patterns
- Real-time data manipulation
- Event-driven architecture

This system demonstrates how classic algorithms can be applied to modern transportation logistics, making it both educationally valuable and practically relevant for understanding airline route optimization systems.

## **Key Implementation Details**

### **Graph Structure:**
- **10 vertices** representing major Colombian cities
- **Weighted edges** with dual attributes (price and time)
- **Adjacency matrix** implementation for efficient lookups
- **Dynamic weight modification** for real-time scenarios

### **Algorithm Applications:**
- **Shortest Path (Dijkstra)**: Route optimization between any two cities
- **Minimum Spanning Tree (Prim)**: City tour planning with minimal cost
- **Graph Traversal**: Comprehensive connectivity analysis

### **Simulation Features:**
- **Weather System**: Probabilistic price increases during rain events
- **Emergency Scenarios**: City isolation for disaster management
- **Promotional System**: Dynamic discount application and restoration
- **Real-time Updates**: Live recalculation of optimal paths

This project represents an excellent example of applying computer science theory to solve practical problems in the transportation industry, demonstrating both technical proficiency and creative problem-solving skills.