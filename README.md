# WorkManagerExample
kotlin workmanager example
![1_VkznGM_XrSK9kmOujJCV6w](https://user-images.githubusercontent.com/15921241/158517101-43bff222-f47f-4a58-a157-1b01bbb780e0.png)
<img width="1006" alt="Screenshot 2022-03-11 at 2 16 44 PM" src="https://user-images.githubusercontent.com/15921241/158517105-f7e74d80-3395-43a0-8db1-b3a2cb1bd50c.png">
<img width="1450" alt="Screenshot 2022-03-11 at 3 48 19 PM" src="https://user-images.githubusercontent.com/15921241/158517112-019ebb94-7e44-4cd1-9aec-c3b4a0975caf.png">
<img width="925" alt="Screenshot 2022-03-11 at 4 32 42 PM" src="https://user-images.githubusercontent.com/15921241/158517117-938e0353-987e-452b-aae8-68485cf61505.png">
![workmanager_main](https://user-images.githubusercontent.com/15921241/158517126-e7b17c6f-c3ce-42e0-9b10-aa3d89fa59e9.svg)



Difference between a coroutine & WorkManager?
Coroutine works only when app is running

Can we create custom constraints to Work Manager?
As of now, not yet

Can we restart the failed Failed Worker?
No, but you can use retry (return Result. RETRY in doWork method) // No restart api available

In MVVM architecture where does WorkManager fits in?
In View Model

What happens to Worker when app gets killed?
If it was running, it will be resumed when conditions are met later
If it was not running, jobScheduler will enqueue it when to be run when condition are met

Can we work chain dynamically?
Yes, you can use append method doe this purpose at run time

Does device reboot affect Work manager execution?
No, they are not affected.


Can we enqueue chain of periodic and one-time work together?.
No, You can’t enqueue chain of periodic and one-time work together.

