# TradeSystem app for JMP D2-D3

## Branching strategy

For each task will be created separate branch with task name (for example JMP7.3-Troubleshooting).
All changes related to task will be stored in appropriate branch. Common changes will be merged to master at once.
Branches for each task will be merged to master every time when task will be completed. Task branches will not be deleted.

## Task descriptions:

1. **JMP7.2-Architecture task:**
    - Use unit tests here ***by.epam.jmp.app.tradesystem.service.impl.UserServiceImplTest*** for verification user operations.
    - Use unit tests here ***by.epam.jmp.app.tradesystem.service.impl.ProductServiceImplTest*** for verification operations with products.

2. **JMP7.3-Troubleshooting task:**
    - Use runner class (***by.epam.jmp.troubleshooting.JMP7_3_Troubleshooting***) for start deadlock simulation.
    - You can find thread dump for this simulation in __**/others/JMP7.3-Troubleshooting_ThreadDump.txt__ file.
    
3. **JMP7.4-Backend:Classloading task**
    - Use maven goal ***package***, compiled class Semaphore.class was placed __**others/Semaphore.class__ here.
    Use tis command for start ***java -jar target/tradesystem.jar ./others/semaphore/v1 by.epam.jmp.backend.classloading.Semaphore***.
    You can find other versions of Semaphore.class in v2 or v3 dirs.