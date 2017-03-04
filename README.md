# TradeSystem app for JMP D2-D3

## Branching strategy

For each task will be created separate branch with task name (for example JMP7.3-Troubleshooting).
All changes related to task will be stored in appropriate branch. Common changes will be merged to master at once.
Branches for each task will be merged to master every time when task will be completed. Task branches will not be deleted.

## Modules information

Each task or structural piece of logic separated to modules. At the moment project contains following modules:
- TradeSystem (root), to execute maven goal 'package' for building all modules.
- TradeSystemCore (jar), contains model, utils, data providers and services
- TradeSystemWeb (war), will be contain web-application
- 3_Troubleshooting (executable jar), contains simulation of deadlock for collecting thread dump
- 4_Classloading (executable jar), contains custom class loader and classloading simulation for
class by.epam.jmp.backend.classloading.Semaphore

List of modules will be updated if new one will be added.

## Task descriptions

1. **JMP7.2-Architecture**
    - See related module __*TradeSystemCore*__.
    - Use unit tests here ***by.epam.jmp.app.tradesystem.core.service.impl.UserServiceImplTest*** for verification user operations.
    - Use unit tests here ***by.epam.jmp.app.tradesystem.core.service.impl.ProductServiceImplTest*** for verification operations with products.

2. **JMP7.3-Troubleshooting**
    - See related module __*3_Troubleshooting*__.
    - Build module and run with cmd/terminal: ***java -jar troubleshooting.jar*** or use runner class
    (***by.epam.jmp.app.troubleshooting.DeadlockSimulator***) for start deadlock simulation.
    - You can find thread dump for this simulation in __**/3_Troubleshooting/other/JMP7.3-Troubleshooting_ThreadDump.txt__ file.
    
3. **JMP7.4-Backend: Classloading**
    - See related module __*4_Classloading*__.
    - Build module, compiled class 'Semaphore' was placed into __**/4_Classloading/other/semaphore/\[v1|v2|v3\]/Semaphore.class__.
    Use cmd/terminal command for start ***java -jar target/classloading.jar ./other/semaphore/v1 by.epam.jmp.backend.classloading.Semaphore***.
    You can find other versions of Semaphore.class in v2 and v3 dirs.

4. **JMP7.5-Architecture: Creational Patterns**
    - See related module __*TradeSystemCore*__.
    - Look into package ***by.epam.jmp.app.tradesystem.core.context***.
    - Checkout 'JMP7.2-Architecture' branch for look into old version of architecture and find factories.