Java Ride-Hailing App Simulation
This project is a console-based simulation of a ride-hailing app backend, designed to demonstrate core Java multithreading and concurrency concepts. It models a scenario where multiple passengers concurrently attempt to book rides with a limited number of available drivers.

The simulation focuses on ensuring safe, concurrent access to shared resources (drivers) and coordinating the lifecycle of multiple threads.

Features
Multithreading: Simulates 20 passengers requesting rides as independent threads of execution.

Resource Management: Uses a Semaphore to manage a pool of 10 drivers, ensuring that no more than 10 rides can be in progress at any given time.

Thread Safety: A ReentrantLock protects the critical section where drivers are assigned from a shared queue, preventing race conditions.

Thread Coordination: An ExecutorService is used to manage the passenger threads, while a CountDownLatch coordinates the main application thread, allowing it to wait for all passengers to complete their booking attempts before shutting down.

Logging: Outputs clear, synchronized messages to the console, showing when a passenger requests a ride, when a match is made, and when a driver is released.

How to Run
Prerequisites: Ensure you have a Java Development Kit (JDK) installed (version 8 or newer).

Compile the Code:
Open your terminal or command prompt, navigate to the directory containing the .java files, and compile them.

javac *.java

Run the Application:
Execute the main class RideHailingApp.

java RideHailingApp

The application will print a series of logs to the console, demonstrating the concurrent booking process. The simulation will automatically terminate once all passenger threads have completed their tasks.

Code Structure
The project is structured using a simple MVC (Model-View-Controller) pattern adapted for a console application:

RideHailingApp.java (Controller): The main entry point. It sets up the simulation by creating the RideBookingService, the ExecutorService, and the CountDownLatch. It then initiates the passenger threads and waits for their completion.

RideBookingService.java (Model): This is the core of the simulation's business logic. It manages the pool of Driver objects and contains the acquireDriver() and releaseDriver() methods, which are protected by a Semaphore and a ReentrantLock.

Driver.java (Model): A simple POJO (Plain Old Java Object) that represents a driver. It holds basic information like an ID and availability status.

Passenger.java (Model/Task): A Runnable class representing a passenger. The run() method contains the logic for a passenger's attempt to book a ride, simulate the ride duration, and then release the driver.

Concurrency Concepts Used
ExecutorService: Replaces manual thread creation and management. It provides a thread pool to execute the Passenger tasks efficiently, handles thread lifecycle, and supports graceful shutdown.

Semaphore: The gatekeeper for our driver pool. Initialized with 10 permits, it ensures that at most 10 passengers can proceed to book a driver at any one time. Other passengers will block and wait at semaphore.acquire() until a permit is released.

ReentrantLock: The bouncer for the shared data structure (availableDriversQueue). When a passenger gets a permit from the semaphore, they still need to safely grab a specific driver object from the queue. The ReentrantLock ensures that only one thread can modify the queue at a time, preventing data corruption.

CountDownLatch: The finish line signal. Initialized with a count of 20 (the number of passengers), each Passenger thread calls countDown() when it completes its run() method. The main thread calls await(), which blocks until the count reaches zero, guaranteeing that all tasks have finished before the application exits.

Potential Improvements
Fairness: The Semaphore and ReentrantLock can be configured to be fair, ensuring that waiting threads are granted access in the order they requested it.

Advanced Driver States: Drivers could have more complex states, such as "on the way to passenger" or "offline."

Dynamic Resources: Implement a feature to add or remove drivers from the pool during runtime.

Real-world Communication: Instead of simple console logging, use a message queue or a concurrent data structure to manage communication between passengers and the booking service.