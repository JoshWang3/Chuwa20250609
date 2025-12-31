
# javascript event loop  javascript 事件循环
To understand the JavaScript Event Loop, one must first acknowledge the central paradox of the language: JavaScript is single-threaded, meaning it can only do one thing at a time, yet it is famous for handling thousands of concurrent operations—like network requests and user interactions—without freezing up.
JavaScript 事件循环，首先必须承认该语言的核心悖论：JavaScript 是单线程的，意味着它一次只能做一件事，但它以处理成千上万的并发操作而闻名——比如网络请求和用户交互——而不会冻结。 
The event loop is the architectural "secret sauce" that resolves this paradox. It is a constantly running process that coordinates how your code is executed, how events are collected and processed, and how queued sub-tasks are handled. Without it, any slow operation (like waiting for a file to download) would block the entire browser, making it completely unresponsive to user input.
事件循环是解决这一悖论的建筑师"秘密调料"。它是一个持续运行的过程，协调你的代码如何执行，事件如何收集和处理，以及排队子任务如何处理。没有它，任何慢速操作（比如等待文件下载）都会阻塞整个浏览器，使其对用户输入完全无响应。 
## The Core Components  核心组件
The event loop doesn't work in isolation; it orchestrates several distinct parts of the JavaScript runtime environment:
事件循环并非独立工作；它协调 JavaScript 运行环境的几个不同部分： 
- **The Call Stack (Synchronous Ground):** This is a LIFO (Last-In, First-Out) data structure that tracks where the program is in its execution. When you call a function, it is pushed onto the stack. When the function returns, it is popped off. Because there is only one stack, if a function takes a long time to run (a "blocking" function), nothing else can happen.
调用栈（同步基础）：这是一个后进先出（LIFO）的数据结构，用于跟踪程序的执行位置。当你调用一个函数时，它会被推入栈中。当函数返回时，它会被弹出。由于只有一个栈，如果函数运行时间过长（一个“阻塞”函数），其他操作将无法进行。
- **Web APIs / Node APIs (The Background Workers):** When you call an asynchronous function like `setTimeout`, `fetch`, or an event listener, JavaScript doesn't handle the waiting itself. It hands these off to the browser (Web APIs) or the system (Node APIs). These environments can use multiple threads in the background to handle the timer or the network request while the main JavaScript thread continues running other code.
Web API / Node API（后台工作线程）：当你调用异步函数如 setTimeout 、 fetch 或事件监听器时，JavaScript 不会自己处理等待。它会将这些任务交给浏览器（Web API）或系统（Node API）。这些环境可以在后台使用多个线程来处理计时器或网络请求，而主 JavaScript 线程继续运行其他代码。
- **The Callback Queue / Macrotask Queue:** Once an asynchronous task finishes (e.g., the 5-second timer is up), its callback function isn't immediately executed. Instead, it is sent to the Callback Queue. It stays there, waiting for its turn to be moved to the call stack.
回调队列 / 宏任务队列：一旦异步任务完成（例如，5 秒计时器到时），其回调函数不会立即执行。相反，它会被发送到回调队列。它将停留在那里，等待被移动到调用栈的机会。
- **The Microtask Queue (The High-Priority Lane):** This is a separate, higher-priority queue used specifically for Promises (`.then()`, `.catch()`, `finally()`) and `queueMicrotask()`. Microtasks are always processed before the next macrotask, even if the macrotask was scheduled earlier.
微任务队列（高优先级通道）：这是一个专门用于处理 Promises（ .then() 、 .catch() 、 finally() ）和 queueMicrotask() 的独立、高优先级队列。微任务总是会在下一个宏任务之前被处理，即使宏任务被安排得更早。 
## How the Loop Operates (Step-by-Step)
事件循环的运作方式（分步详解）
The event loop is essentially an infinite loop that performs the following checks in order:
事件循环本质上是一个无限循环，按顺序执行以下检查： 
1. **Execute the Call Stack:** The loop waits until the call stack is completely empty.
执行调用栈：循环会等待直到调用栈完全为空。
2. **Drain the Microtask Queue:** Once the stack is clear, the event loop looks at the Microtask Queue. It executes every single task currently in that queue, one by one, until the queue is entirely empty.
清空微任务队列：一旦栈为空，事件循环会查看微任务队列。它会逐个执行队列中的每个任务，直到队列完全为空。
3. **Process One Macrotask:** After the microtasks are cleared, the loop takes the **oldest single task** from the Macrotask Queue (like a `setTimeout` callback) and pushes it onto the call stack to be executed.
处理一个宏任务：在微任务清空后，循环从宏任务队列（如 setTimeout 回调）中取出最老的单个任务，并将其推入调用栈以执行。
4. **Repeat:** The cycle starts over, checking if new microtasks were added during that last macrotask execution.
重复：循环重新开始，检查在最后一次宏任务执行期间是否添加了新的微任务。 
## Practical Implications for Developers
对开发者的实际影响
Understanding this mechanism explains several "weird" behaviors in JavaScript:
理解这个机制可以解释 JavaScript 中的一些"奇怪"行为：
- **`setTimeout(fn, 0)` isn't instant:** When you set a delay of 0ms, you aren't saying "run this now." You are saying "put this in the callback queue as soon as possible". It will still wait for the call stack to be empty and for all pending microtasks to finish.
`setTimeout(fn, 0)` 不是立即执行：当你设置延迟为 0 毫秒时，你并不是说"立即执行这个"。你是在说"尽快把这个放入回调队列"。它仍然需要等待调用栈为空并且所有挂起的微任务完成。
- **Microtask Starvation:** Because the event loop will not move on to the next macrotask until the Microtask Queue is empty, you can accidentally "starve" the event loop by continuously adding new microtasks (e.g., a promise that resolves and immediately calls another promise). This will freeze the UI because the browser never gets a chance to process user clicks or rendering tasks.
微任务饥饿：由于事件循环不会在微任务队列清空之前进入下一个宏任务，通过不断添加新的微任务（例如，一个解析后立即调用另一个承诺的承诺），你可能会意外地"饿死"事件循环。这将冻结 UI，因为浏览器永远没有机会处理用户点击或渲染任务。
- **UI Blocking:** Since the browser's rendering process also typically happens between event loop ticks, long-running synchronous code on the call stack prevents the screen from updating. This is why heavy computations should be broken into smaller chunks or offloaded to Web Workers.
UI 阻塞：由于浏览器的渲染过程通常也发生在事件循环的每次迭代之间，调用栈上的长时间运行同步代码会阻止屏幕更新。这就是为什么复杂的计算应该分解成更小的块或卸载到 Web Workers 上。 

In summary, the event loop is the traffic controller of the JavaScript world. It ensures that synchronous "important" work happens first, followed by "high-priority" promises, and finally "lower-priority" background tasks like timers and I/O, all while keeping the application feeling fluid and responsive.
总之，事件循环是 JavaScript 世界的交通指挥官。它确保同步的"重要"工作首先执行，其次是"高优先级"的 Promise，最后是"低优先级"的背景任务，如计时器和 I/O，同时保持应用程序流畅且响应迅速。

If you are looking for a classic visual walkthrough, Philip Roberts' talk ["What the heck is the event loop anyway?"](https://www.youtube.com/watch?v=8aGhZQkoFbQ) remains one of the most highly recommended resources for developers.
如果你正在寻找经典的视觉演示，Philip Roberts 的演讲《What the heck is the event loop anyway?》仍然是开发者们强烈推荐的一种资源。

# Do you mean it will follow the order "empty call stack->empty  microtask queue->process one macrotask->empty call stack->empty  microtask queue->process one macrotask..."?
你是否指的是它将遵循"空调用栈->空微任务队列->处理一个宏任务->空调用栈->空微任务队列->处理一个宏任务..."的顺序？

Yes, that is a highly accurate way to visualize the cycle. As of 2025, the JavaScript event loop continues to follow this strict priority model to ensure that high-priority "micro" updates (like Promise resolutions) occur before the engine moves on to the next independent "macro" block of work.
2025 年，JavaScript 事件循环继续遵循严格的优先级模型，以确保高优先级的“微”更新（如 Promise 的解决）在引擎继续处理下一个独立的“宏”工作块之前发生。 
## The Standard Cycle of the Event Loop
事件循环的标准周期
The precise sequence you described can be formalized into these repeating phases:
你描述的精确顺序可以形式化为这些重复的阶段：
1. **Empty the Call Stack:** The engine executes all currently running synchronous code. Nothing else can happen until the Call Stack is totally empty.
清空调用栈：引擎执行所有当前正在运行的同步代码。在调用栈完全清空之前，不会发生任何其他操作。
2. **Drain the Microtask Queue:** Once the stack is clear, the event loop looks at the Microtask Queue. It will execute every single task in this queue until it is completely empty.
   清空微任务队列：一旦栈变空，事件循环会查看微任务队列。它会执行队列中的每一个任务，直到队列完全为空。
    1. Critical Detail: If a microtask itself schedules another microtask, the new one is added to the end of the current queue and will also be executed before the loop moves on.
    关键细节：如果微任务本身又调度了另一个微任务，新的任务会被添加到当前队列的末尾，并且也会在事件循环继续之前被执行。
3. **Perform Rendering (Optional):** In browser environments, the browser typically attempts to re-render the UI at this stage, but only if it is time for a new frame (usually every 16.6ms for 60fps).
执行渲染（可选）：在浏览器环境中，浏览器通常尝试在这个阶段重新渲染 UI，但只有在新帧（通常为 60fps 时每 16.6ms）到来时才会这样做。
4. **Process ONE Macrotask:** The event loop then picks the oldest single task from the Macrotask Queue (e.g., a setTimeout callback or an I/O event) and pushes it onto the Call Stack.
处理一个宏任务：事件循环随后从宏任务队列中挑选最旧的单一任务（例如，一个 setTimeout 回调或一个 I/O 事件），并将其推入调用栈。
5. **Repeat:** After that one macrotask finishes and the stack is empty again, the loop immediately goes back to Step 2 to check for new microtasks.
重复：当这个宏任务完成后，栈再次为空，循环立即回到步骤 2 检查新的微任务。 
## Illustrative Example  示例说明
If you ran the following code, the event loop would handle it in exactly the order you proposed:
如果你运行以下代码，事件循环将按照你提出的顺序处理：
```javascript
console.log("1: Synchronous"); // (1) Call Stack

setTimeout(() => {
    console.log("2: Macrotask"); // (4) Macrotask Queue
}, 0);

Promise.resolve().then(() => {
    console.log("3: Microtask"); // (2) Microtask Queue
});

console.log("4: Synchronous"); // (1) Call Stack
```
### Execution Order:  执行顺序：
- **Step 1 (Call Stack):** Prints `1: Synchronous` and `4: Synchronous`. The stack is now empty.
步骤 1（调用栈）：打印 1: Synchronous 和 4: Synchronous 。栈现在为空。
- **Step 2 (Microtask Queue):** The loop sees the Promise callback and prints `3: Microtask`. The Microtask Queue is now empty.
步骤 2（微任务队列）：循环检测到 Promise 回调并打印 3: Microtask 。微任务队列现在为空。
- **Step 3 (One Macrotask):** The loop picks the first task from the Macrotask Queue and prints `2: Macrotask`.
步骤 3（一个宏任务）：循环从宏任务队列中选取第一个任务并打印 2: Macrotask 。
## Why does it work this way?
为什么是这样工作的？
- **Consistency:** Microtasks are intended for things that need to happen "immediately after" the current execution but without the overhead of a full new event loop turn.
一致性：微任务用于处理需要在当前执行完成后立即发生的事情，但无需承担完整新事件循环的额外开销。
- **Performance:** By processing all microtasks at once, JavaScript ensures that the application state is fully updated before the browser tries to draw pixels on the screen.
性能：通过一次性处理所有微任务，JavaScript 确保应用程序状态在浏览器尝试在屏幕上绘制像素之前完全更新。
- **Avoid "Starvation":** Processing only one macrotask at a time prevents long-running macrotasks from piling up and completely blocking the microtask queue or rendering.
避免“饥饿”：一次只处理一个宏任务可以防止长时间运行的宏任务堆积，从而完全阻塞微任务队列或渲染。 

This "one macrotask, then all microtasks" rule is the fundamental reason why `await` and `.then()` always feel "faster" or more immediate than `setTimeout(..., 0)`.
这个“一个宏任务，然后所有微任务”的规则是 await 和 .then() 总是感觉比 setTimeout(..., 0) “更快”或更即时的基本原因。

# In your first answer, you said "Repeat: The cycle starts over, checking if new microtasks were added during that last macrotask execution.", why it doesn't check if new call stack task is added?
在第一个回答中，你说“重复：周期重新开始，检查在最后一次宏任务执行期间是否添加了新的微任务。”，为什么它不检查是否添加了新的调用栈任务？
That is an excellent observation. The reason the event loop doesn't specifically "check for new call stack tasks" at the start of a cycle is because of how the Call Stack itself functions: **it is not a queue where tasks wait; it is the active place where tasks are currently being executed.**
调用栈本身的功能：它不是一个任务等待的队列；它是当前正在执行任务的活跃场所。 
The event loop's job is specifically to fill that empty stack from the queues. Here is a deeper breakdown of why "new call stack tasks" don't exist independently:
事件循环的工作就是从队列中填充那个空栈。以下是"新的调用栈任务"不存在独立性的更深层解释： 
## 1. The Call Stack is the Engine, Not a Queue
调用栈是引擎，不是队列
In JavaScript, the "Call Stack" is simply a mechanism to keep track of function execution context.
在 JavaScript 中，"调用栈"只是一个用来跟踪函数执行上下文的机制。 
- **Synchronous Code is the first "Task":** When a script starts, the entire script is treated as one large "task" (a macrotask). The engine pushes this script onto the stack and runs it until it finishes.
同步代码是第一个"任务"：当脚本开始时，整个脚本被视为一个大的"任务"(宏任务)。引擎将这个脚本推入栈中并运行它直到完成。
- **No "Incoming" Stack Tasks:** There is no "Call Stack Queue" where new synchronous tasks sit waiting to be discovered. If a piece of code is synchronous, it is already part of a function currently running on the stack. If it is asynchronous, it is sent to a Queue (Micro or Macro) to wait for its turn.
没有"待处理的"栈任务：没有"调用栈队列"让新的同步任务等待被发现。如果一段代码是同步的，它已经是当前在栈上运行的函数的一部分。如果它是异步的，它会被发送到队列(微任务或宏任务)中等待它的轮次。 
## 2. How the Event Loop "Feeds" the Stack
事件循环如何"喂"给栈
Instead of the event loop "checking" for new call stack tasks, the loop's entire purpose is to move tasks into the Call Stack so they can be executed.
事件循环不是去"检查"新的调用栈任务，循环的整个目的是将任务移入调用栈以便执行。 
- **The Check:** The event loop continuously asks: "Is the Call Stack empty?".
检查：事件循环不断询问："调用栈是否为空？"
- **The Action:** Only when the answer is "Yes" does the loop go to the Microtask Queue or Macrotask Queue to find the next piece of code to run.
操作：只有当答案是"是"时，循环才会去微任务队列或宏任务队列中寻找下一块要执行的代码。
- **The Execution:** When it finds a task (like a `.then()` callback), it pushes that function onto the Call Stack. At that moment, the stack is no longer empty, and the event loop "waits" while the code on the stack runs to completion.
执行：当找到任务（比如一个回调函数 .then() ）时，它会将该函数推入调用栈。此时，栈不再为空，事件循环"等待"栈上的代码执行完成。 
## 3. The "One-Way Street" for New Code
新代码的"单行道"
As of 2025, the only way for "new" code to enter the system is through the Web APIs (like a timer finishing or a user clicking a button).
截至 2025 年，"新"代码进入系统的唯一方式是通过 Web API（例如计时器完成或用户点击按钮）。 
- These APIs never push code directly onto the Call Stack. If they did, they would interrupt your code in the middle of a line, causing unpredictable errors.
这些 API 永远不会直接将代码推送到调用栈上。如果它们这样做，它们会在代码行中间中断你的代码，导致不可预测的错误。
- Instead, they always place callbacks into a Queue.
相反，它们总是将回调函数放入队列中。 
## The Refined Mental Model
精炼的思维模型
Think of the event loop as a waiter in a restaurant:
将事件循环想象成餐厅里的服务员：
- The Call Stack is the customer's table.
调用栈是顾客的餐桌。
- The Waiter (Event Loop) can only bring a new dish (task) to the table once the table is completely cleared.
服务员（事件循环）只有当餐桌完全清理干净后，才能将一道新菜（任务）端到桌上。
- The waiter doesn't check "if a new customer is at the table" because customers can only get to the table by being seated by the waiter from the waiting line (the Queues).
服务员不会检查"桌上是否有一位新顾客"，因为顾客只能通过服务员从等候队列（队列）中安排入座才能到达餐桌。 

In summary: The event loop doesn't check for "new call stack tasks" because nothing can get onto the stack except through the event loop itself once it has cleared the previous work. The loop checks the Queues to decide what should go on the Stack next.
总之：事件循环不会检查“新的调用栈任务”，因为在它清除之前的工作后，没有任何东西能通过事件循环本身进入栈中。循环检查队列以决定下一步应该将什么放入栈中。