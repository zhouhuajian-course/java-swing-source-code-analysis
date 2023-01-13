git # Java Swing 源码分析

## Java 版本

Java 17

## Java Swing 文档

https://docs.oracle.com/javase/tutorial/uiswing/index.html

## Swing API 18个公共包

The Swing API is powerful, flexible — and immense. The Swing API has 18 public packages:

+ javax.accessibility	
+ javax.swing.plaf	
+ javax.swing.text
+ javax.swing	javax.swing.plaf.basic	
+ javax.swing.text.html
+ javax.swing.border	
+ javax.swing.plaf.metal	
+ javax.swing.text.html.parser
+ javax.swing.colorchooser	
+ javax.swing.plaf.multi	javax.swing.text.rtf
+ javax.swing.event	
+ javax.swing.plaf.synth	
+ javax.swing.tree
+ javax.swing.filechooser	
+ javax.swing.table	
+ javax.swing.undo

## java.awt.peer包下的XxxPeer

peer 同龄人；同辈；同等社会地位（或能力）的人

peer 对等设计模式

![java.awt.peer.png](resources/java.awt.peer.png)

AWT Java 1.x 内置的库，使用的技术是对等设计模式 Peer

该设计模式将awt控件直接对应到运行平台上的一个类似或等同控件。例如Button类对应Windows的标准Button。

对等模式用于在两个控件间建立一个相互作用的关系。

AWT首先经过通用的Java技术来控制图形、事件等，然后Java虚拟机再将请求传送到具体的平台图形和控件接口去交互。

对等模式，SWT必须使用操作系统图形接口功能的交集，为了保证移植性，只能使用所有系统都支持的特性，所以AWT功能较少，图形难看，这是为了保证移植性而做出的牺牲。

Swing Java 1.2 

Swing一些底层类使用AWT的Component、Container、Window等类，可能是为了保持与AWT兼容，方便大家将代码移植到Swing上

Swing不再沿用Peer对等模式来实现GUI界面，完全基于Java自绘制图形实现。Swing界面和Windows界面不再有任何类似，尤其是窗口控件样式，但可以通过换肤来达到模拟Windows界面的效果。

Swing的结构庞大而且复杂，模式和结构会相对比较难理解，应为很多类会继承自AWT相关的类。

Swing控件都是利用Java图形功能绘制出来，而不是对应到平台的一个具体控件实现，所有Swing控件都是直接或间接用Graphics绘制出来，好处是，想要什么控件，直接绘制。这样做可以不牺牲移植性的基础上，加入丰富的界面交互功能。

缺点效率低。

1. Swing类层次太深，一个JFrame经过4、5层类继承关系，再加上虚拟机的图形功能内部实现，大概6曾转接关系；

    ```
    java.lang.Object
     java.awt.Component
      java.awt.Container
       java.awt.Window
        java.awt.Frame
         javax.swing.JFrame
    ```
   
2. Swing基于自绘制技术，为了保持可移植性，可能无法使用硬件加速和平台特性来加快图形操作的速度。Java是“高层”的图形技术，没有底层做图形速度快。

## Swing 使用拖拽的方式设计界面

可以把程序开发的重点放在逻辑处理上

## setVisible方法

setVisible方法并不会阻塞当前线程，
使用了其他线程来进行事件监听

## 调试注意点

需要有断点才能看到线程调用栈情况

## Swing 顶级容器 Top-Level Container

As we mentioned before, Swing provides three generally useful top-level container classes: JFrame, JDialog, and JApplet. When using these classes, you should keep these facts in mind:

+ To appear onscreen, every GUI component must be part of a containment hierarchy. A containment hierarchy is a tree of components that has a top-level container as its root. We'll show you one in a bit.
+ Each GUI component can be contained only once. If a component is already in a container and you try to add it to another container, the component will be removed from the first container and then added to the second.
+ Each top-level container has a content pane that, generally speaking, contains (directly or indirectly) the visible components in that top-level container's GUI.
+ You can optionally add a menu bar to a top-level container. The menu bar is by convention positioned within the top-level container, but outside the content pane. Some look and feels, such as the Mac OS look and feel, give you the option of placing the menu bar in another place more appropriate for the look and feel, such as at the top of the screen.

Note: Although JInternalFrame mimics JFrame, internal frames aren't actually top-level containers.

组件树结构非常重要

![containment_hierarchy.png](resources/containment_hierarchy.png)

JFrame 的内容面板 Content Pane 继承 JComponent，使用 BorderLayout

JFrame的add remote setLayout方法会自动调用ContentPane的相应方法，为了方便用户使用。

```
Note: 
As a convenience, the add method and its variants, remove and setLayout have been overridden to forward to the contentPane as necessary. This means you can write

frame.add(child);
and the child will be added to the contentPane.

Note that only these three methods do this. This means that getLayout() will not return the layout set with setLayout().
```
## 布局管理器 Layout Manager

JFrame的Content Pane 默认 BorderLayout
JPanel 默认 FlowLayout

## 事件分发线程

AWT-EventQueue-0

## JFrame的add，其实是JFrame.contentPane的add

JFrame.add() -> Container.add() -> JFrame.addImpl()

```java
public class JFrame  extends Frame implements WindowConstants, Accessible, RootPaneContainer, TransferHandler.HasGetTransferHandler 
{
    // ...
    protected void addImpl(Component comp, Object constraints, int index)
    {
        if (isRootPaneCheckingEnabled()) {
            getContentPane().add(comp, constraints, index);
        }
        else {
            super.addImpl(comp, constraints, index);
        }
    }
}
    
```

## Swing Components 组件

Swing 所有以“J”开头的组件，除了顶级容器，都继承自 JComponent

JFrame和JDialog不是继承JComponent，因为他们要实现implement顶级容器

JComponent 继承 Container， Container 继承 Component

```
java.lang.Object
  java.awt.Component
    java.awt.Container
      javax.swing.JComponent
```

## 组件可见性

顶级容器组件默认不可见，其他组件默认可见

void setVisible(boolean)
boolean isVisible()

Set or get whether the component is visible. Components are initially visible, with the exception of top-level components.

## Text Components 文本组件

Swing 的文本组件都是继承自 JTextComponent

![JTextComponet.hierarchy.png](resources/JTextComponet.hierarchy.png)

## Swing Component Examples

组件例子

https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

## JFrame ContentPane 

JFrame的ContentPane是JPanel，默认使用BorderLayout

constraints默认是BorderLayout.CENTER，也就是默认添加组件到中间区域

```java
public class JRootPane extends JComponent implements Accessible {
    protected Container createContentPane() {
        JComponent c = new JPanel();
        c.setName(this.getName()+".contentPane");
        c.setLayout(new BorderLayout() {
            /* This BorderLayout subclass maps a null constraint to CENTER.
             * Although the reference BorderLayout also does this, some VMs
             * throw an IllegalArgumentException.
             */
            public void addLayoutComponent(Component comp, Object constraints) {
                if (constraints == null) {
                    constraints = BorderLayout.CENTER;
                }
                super.addLayoutComponent(comp, constraints);
            }
        });
        return c;
    }
}
   
```

## JApplet

JApplet依赖浏览器来执行。

很多浏览器都可以支持JApplet，包括了IE，Firefox等。

Java 9 开始废弃

## Swing 按钮 Buttons

继承自 AbstractButton

JMenuItem也是一种按钮

|Class|Summary|
|---|---|
|JButton     	        |A common button.	|                 
|JCheckBox	            |A check box button.|	                         
|JRadioButton	        |One of a group of radio buttons.|	     
|JMenuItem	            |An item in a menu.	|
|JCheckBoxMenuItem	    |A menu item that has a check box.|	
|JRadioButtonMenuItem	|A menu item that has a radio button.|	
|JToggleButton	        |Implements toggle functionality inherited by JCheckBox and JRadioButton. Can be instantiated or subclassed to create two-state buttons.|


## JFrame 屏幕居中

frame.setLocationRelativeTo(null);

## Swing 线程

A Swing programmer deals with the following kinds of threads:

+ Initial threads, the threads that execute initial application code.
+ The event dispatch thread, where all event-handling code is executed. Most code that interacts with the Swing framework must also execute on this thread.
+ Worker threads, also known as background threads, where time-consuming background tasks are executed.

Why does not the initial thread simply create the GUI itself? Because almost all code that creates or interacts with Swing components must run on the event dispatch thread. This restriction is discussed further in the next section.

为什么不简单使用初始化线程开创建GUI，因为几乎所有创建和交互Swing组件的代码，必须运行在事件分发线程。

## Swing 组件 MVC

JTextComponent
+ A model, known as a document, that manages the component's content.
+ A view, which displays the component on screen.
+ A controller, known as an editor kit, that reads and writes text and implements editing capabilities with actions.

## Swing 事件

+ 事件源    一般是组件或模型
+ 事件      由事件源创建，是一个对象，由事件信息，可以识别出事件源
+ 事件监听器 监听、处理事件

Each event is represented by an object that gives information about the event and identifies the event source. Event sources are often components or models, but other kinds of objects can also be event sources.

![event.png](resources/event.png)

EventObject 所有 AWT Swing事件的父类

事件分两类 
+ 低级事件 例如鼠标 键盘事件
+ 语义事件 例如Action Item事件

```
Concepts: Low-Level Events and Semantic Events
Events can be divided into two groups: low-level events and semantic events. Low-level events represent window-system occurrences or low-level input. Everything else is a semantic event.

Examples of low-level events include mouse and key events both of which result directly from user input. Examples of semantic events include action and item events. A semantic event might be triggered by user input; for example, a button customarily fires an action event when the user clicks it, and a text field fires an action event when the user presses Enter. However, some semantic events are not triggered by low-level events, at all. For example, a table-model event might be fired when a table model receives new data from a database.

Whenever possible, you should listen for semantic events rather than low-level events. That way, you can make your code as robust and portable as possible. For example, listening for action events on buttons, rather than mouse events, means that the button will react appropriately when the user tries to activate the button using a keyboard alternative or a look-and-feel-specific gesture. When dealing with a compound component such as a combo box, it is imperative that you stick to semantic events, since you have no reliable way of registering listeners on all the look-and-feel-specific components that might be used to form the compound component.
```

## 事件适配器 Event Adapters

某些Listener有多个方法，如果只想实现个别方法，可用适配器

## ImageIcon 图标

javax.swing.ImageIcon

## JToolBar

工具栏的高度是不固定的，根据工具栏里面的内容的高度确定工具栏高度。

工具栏里面的组件，可以是普通按钮、图标按钮、文本输入框...

工具栏特点，左侧有一些点点，可以拖动到四边，还可以拖出窗口，变成一个小窗口。

## 事件分发线程 

启动入口

1. SwingUtilities.invokeLater() 可以触发事件线程启动
2. JFrame.setVisible() 也可以触发事件线程启动   

事件线程类 EventDispatchThread extends Thread

java.awt.EventQueue

```java
    final void initDispatchThread() {
        pushPopLock.lock();
        try {
            if (dispatchThread == null && !threadGroup.isDestroyed() && !appContext.isDisposed()) {
                dispatchThread = AccessController.doPrivileged(
                    new PrivilegedAction<EventDispatchThread>() {
                        public EventDispatchThread run() {
                            EventDispatchThread t =
                                new EventDispatchThread(threadGroup,
                                                        name,
                                                        EventQueue.this);
                            t.setContextClassLoader(classLoader);
                            t.setPriority(Thread.NORM_PRIORITY + 1);
                            t.setDaemon(false);
                            AWTAutoShutdown.getInstance().notifyThreadBusy(t);
                            return t;
                        }
                    }
                );
                dispatchThread.start();
            }
        } finally {
            pushPopLock.unlock();
        }
    }
```

大多数Swing的API非线程安全，不能在任意地方调用，应该只在EDT中调用。
   
Swing的线程安全靠事件队列和EventDispatchThread来保证。 

Swing将GUI请求放入一个事件队列中执行。通过EDT，使得非线程安全的Swing函数库避开了并发问题。

## Swing中的线程分类
一个Swing程序中一般有下面三种类型的线程：

1. 初始化线程（Initial Thread）
每个程序必须有一个main方法作为程序的入口。
该方法运行在初始化或启动线程上。初始化线程读取程序参数并初始化一些对象。
在许多Swing程序中，该线程主要目的是启动程序的GUI。创建UI的点，也就是程序开始将控制权转交给UI时的点。
一旦GUI启动后，对大多数事件驱动的桌面程序，初始化线程的工作就结束了。
   
2. UI事件调度线程（EDT）
Swing程序只有一个EDT，负责GUI组件的绘制和更新，调用程序的事件处理器来响应用户交互。
所有事件处理都是在EDT执行，程序同UI组件和其基本数据模型的交互只允许在EDT上进行。
所有运行在EDT上的任务应该尽快完成，以便UI能及时响应用户输入。
   
3. 任务线程（Worker Thread）


单一线程的事件队列的特性

将同步操作转为异步操作
将并行处理转换为串行顺序处理

EDT要处理所有GUI操作

职责明确，任何GUI请求都应该在EDT中调用
要处理的GUI请求非常多，包括窗口移动、组件自动重绘、刷新，它很忙。任何与GUI无关的处理不要由EDT执行，尤其是I/O耗时操作

*备注：这部分来自网络*

## Swing 编程铁律

1. 必须通过EDT刷新组件
从其他线程访问UI组件及其事件处理器会导致界面更新和绘制错误

2. 禁止在EDT执行其他耗时操作
在EDT上执行耗时任务会使程序失去响应，这会使GUI事件阻塞在队列中得不到处理

3. 耗时操作放在独立的任务线程
通过SwingWorker启动。应使用独立的任务线程来执行耗时计算或输入输出密集型任务。

比如同数据库通信
访问网站资源、读写大树据量的文件。
任何干扰或延迟UI事件的处理只应出现在独立任务线程中。


在初始化线程（即禁止在main方法中直接创建Frame，在初始化线程中应使用invokeLater初始化GUI）
任务线程同Swing组件或其缺省数据模型进行的交互

都是非线程安全性操作。

通过SwingWorker类的管理，隔离EDT和任务线程，使它们各负其责


EDT 绘制和更新界面，并响应用户输入
任务线程，执行和界面无直接关系的耗时任务和I/O密集型操作


EDT要处理所有GUI操作，它是职责分明且非常忙碌的。也就是说你要记住两条原则：一、职责分明，任何GUI请求都应该在EDT中调用。二、需要处理的GUI请求非常多，包括窗口移动、组件自动重绘、刷新，它很忙，所以任何与GUI无关的处理不要由EDT来负责，尤其是I/O这种耗时的操作。

invokeLater和invokeAndWait：前文提到，Swing自身不是线程安全，对非EDT的并发调用需通过 invokeLater(runnable)和invokeAndWait(runnable)使请求插入到队列中等待EDT去执行。 invokeLater(runnable)方法是异步的，它会立即返回，具体何时执行请求并不确定，所以命名invokeLater是稍后调用。invokeAndWait(runnable)方法是同步的，它被调用结束会立即block当前线程（调用invokeAndWait的那个线程）直到EDT处理完那个请求。invokeAndWait一般的应用是取得Swing组件的数据，

为什么要有这样一条限制？结合前文不难得出-防止死锁。如果invokeAndWait在EDT中调用，那么首先将请求压进队列，然后EDT便被 block（因为它就是调用invokeAndWait的当前线程）等待请求结束通知它继续运行，而实际上请求将永远得不到执行，因为它在等待队列的调度 使EDT执行它，这就陷入一个僵局-EDT等待请求先执行，请求又等待EDT对队列的调度。彼此等待对方释放锁是造成死锁的四类条件之一。Swing有意 地避免了这类情况的发生。

Swing的事件分发线程 从事件队列中获得事件，然后进行事件分发。在分发过程中通过事件获得事件源，在通过事件源通知到相应的监听器。大致关系如下：事件分发线程 -> 事件-> 事件源->监听器

*备注：这部分来自网络*

## SwingUtilities. invokeAndWait(runnable)

同步的，它被调用结束会立即阻塞当前线程，直到EDT处理完该请求。

一般用于取得Swing组件的数据。

## 8.2 SwingUtilities. invokeLater(runnable)

使 doRun.run() 在AWT事件分发线程上异步执行。所有待处理的AWT事件被执行后，就会发生这种情况。当应用程序线程需要更新GUI时，应使用此方法。

## schedule 和 dispatch 区别

用“主要业务逻辑”做在哪里来区分。

schedule:  调度。 scheduler是具有重业务逻辑的，也就是调度算法。例如，linux kernel从中断出来后，会切入到scheduler routine，因为这里面的逻辑很多（CFS，RR，FIFO等调度算法），所以叫做 调度。

dispatch: 分发、派发。 dispatcher是轻业务逻辑或者没有业务逻辑，纯粹简单的转发、派发。例如，负载均衡的dispatcher收到请求后，通过哈希表找到response server，直接将请求派发下去，本身并不做业务逻辑。

## 其他杂谈

同时使读者消除长期以来“Swing性能低、界面丑陋”诸如此类的旧观念。

在新式的JDK中，Swing已经在性能方面改进了很多，完全可以这么说：与应用程序自身的业 务计算相比，界面上的耗时可以忽略。但是如果上述恶习改不掉的话，Swing永远“快”不起来，SWT也同样如此，因为它们都是单线程图形工具包。

*本部分来自网络*

## Swing中的事件机制和观察者模式

事件机制的三个基本要素
1. 事件（event） 通常是指某种类型的操作。例如单击了一次Button或者文本框中输入了一个字符，则单击、输入字符 就是事件

2. 事件源(Event Source)：可以简单的将其理解为事件发生的源头，例如单击了一次Button，由于单击是在Button上发生的，所以Button就是事件发生源头，即事件源。在Swing中，通常包含了所有能交互的的组件

3. 事件监听器(Listener)：是事件处理机制的核心，定义了所有事件处理的相关逻辑。事件监听器的主要作用，是关注可能发生事件的对象（事件源），并在其发生了特定的事件后，能对其做出反应

事件监听机制与观察者模式之间的联系

从事件监听器(Listener)的作用上分析，监听器的功能就是能够感知到对象上发生了某个事件。换句话说，就是当事件源上发生了某个事件时，监听器希望能被通知到，并且在得到通知后能对其做出相应的处理。而这实际上就是观察者模式中观察者所希望的事情。因此，我们可以很自然将监听者映射到观察者模式中观察者（Observer），那么事件源和事件就相应的映射到了观察者模式中的目标（Subject）。

下面就从源码上验证上述结论：

Subject的一些必要条件

+ 知道他的观察者。（维护观察者列表）
+ 提供注册和删除观察者的接口
+ 当状态发生变化时，向观察者发送通知

Observer的一些必要条件

+ 当目标发生变化时，提供一个更新的接口（回调函数）

```java
public abstract class JComponent extends Container implements Serializable,
                                              TransferHandler.HasGetTransferHandler {
   /** A list of event listeners for this component. */
   // JComponent 维护了组件的所有事件监听器
   protected EventListenerList listenerList = new EventListenerList();
}

```

```java
public abstract class AbstractButton extends JComponent implements ItemSelectable, SwingConstants {

    /**
     * Adds an <code>ActionListener</code> to the button.
     * @param l the <code>ActionListener</code> to be added
     */
    // 管理监听者 或者叫做观察者
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    /**
     * Removes an <code>ActionListener</code> from the button.
     * If the listener is the currently set <code>Action</code>
     * for the button, then the <code>Action</code>
     * is set to <code>null</code>.
     *
     * @param l the listener to be removed
     */
    public void removeActionListener(ActionListener l) {
        if ((l != null) && (getAction() == l)) {
            setAction(null);
        } else {
            listenerList.remove(ActionListener.class, l);
        }
    }

    /**
     * Returns an array of all the <code>ActionListener</code>s added
     * to this AbstractButton with addActionListener().
     *
     * @return all of the <code>ActionListener</code>s added or an empty
     *         array if no listeners have been added
     * @since 1.4
     */
    @BeanProperty(bound = false)
    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.class);
    }
}
```

```java
// java.util.EventListener
public interface EventListener {
}
// 未定义任何方法，实际上作用就是一个标记类，用于身份说明

public interface ActionListener extends EventListener {

   public void actionPerformed(ActionEvent e);

}
```

```java
// java.util.EventObject 事件类的父类
public class EventObject implements java.io.Serializable {
   /**
    * The object on which the Event initially occurred.
    */
   protected transient Object source;

   /**
    * Constructs a prototypical Event.
    *
    * @param source the object on which the Event initially occurred
    * @throws IllegalArgumentException if source is null
    */
   public EventObject(Object source) {
      if (source == null)
         throw new IllegalArgumentException("null source");

      this.source = source;
   }

   /**
    * The object on which the Event initially occurred.
    *
    * @return the object on which the Event initially occurred
    */
   public Object getSource() {
      return source;
   }
}

public class ActionEvent extends AWTEvent {
   // ...
}
```

实际上event做的事情不止这些，他是事件发起者和事件接收者之间的桥梁。从观察者模式的角度上说，就是触发者与观察者之间的桥梁。（触发者：改变目标（subject）状态的对象）。

在事件监听机制中，监听器一般不会主动去触发一些事件（这一点与观察者不同，在观察者设计模式中，观察者可以直接改变目标的状态）。事件往往是由系统触发的，例如点击事件，敲击键盘的事件等等。监听者往往作为一个被动通知的对象。系统会实时捕捉一些事件，然后将其放到一个事件队列当中，然后Swing会单独开启一个线程，从事件队列中获得事件，然后对事件进行分发，最终通过事件中维护的事件源通知监听者。

```java
class EventDispatchThread extends Thread {

   /*
    * Must be called on EDT only, that's why no synchronization
    */
   public void stopDispatching() {
      doDispatch = false;
   }

   public void run() {
      try {
         pumpEvents(new Conditional() {
            public boolean evaluate() {
               return true;
            }
         });
      } finally {
         getEventQueue().detachDispatchThread(this);
      }
   }

   void pumpOneEventForFilters(int id) {
      
         eq.dispatchEvent(event);
      
   }
}

public class EventQueue {
   protected void dispatchEvent(final AWTEvent event) {
      //...
               dispatchEventImpl(event, src);
      //...         
   }

   private void dispatchEventImpl(final AWTEvent event, final Object src) {
      event.isPosted = true;
      if (event instanceof ActiveEvent) {
         // This could become the sole method of dispatching in time.
         setCurrentEventAndMostRecentTimeImpl(event);
         ((ActiveEvent)event).dispatch();
      } else if (src instanceof Component) {
          // 交给具体的组件去分发事件 dispatchEvent
         ((Component)src).dispatchEvent(event);
         event.dispatched();
      } else if (src instanceof MenuComponent) {
         ((MenuComponent)src).dispatchEvent(event);
      }
      // ...
   }
}


```


```java
public abstract class Component implements ImageObserver, MenuContainer,
        Serializable
{
    /**
     * Dispatches an event to this component or one of its sub components.
     * Calls {@code processEvent} before returning for 1.1-style
     * events which have been enabled for the {@code Component}.
     * @param e the event
     */
    public final void dispatchEvent(AWTEvent e) {
        dispatchEventImpl(e);
    }
}

// 以AbstractButton为例，最后交给组件处理事件
public abstract class AbstractButton extends JComponent implements ItemSelectable, SwingConstants {
   /**
    * Notifies all listeners that have registered interest for
    * notification on this event type.  The event instance
    * is lazily created using the <code>event</code>
    * parameter.
    *
    * @param event  the <code>ActionEvent</code> object
    * @see EventListenerList
    */
   protected void fireActionPerformed(ActionEvent event) {
      // Guaranteed to return a non-null array
      Object[] listeners = listenerList.getListenerList();
      ActionEvent e = null;
      // Process the listeners last to first, notifying
      // those that are interested in this event
      for (int i = listeners.length-2; i>=0; i-=2) {
         if (listeners[i]==ActionListener.class) {
            // Lazily create the event:
            if (e == null) {
               String actionCommand = event.getActionCommand();
               if(actionCommand == null) {
                  actionCommand = getActionCommand();
               }
               e = new ActionEvent(AbstractButton.this,
                       ActionEvent.ACTION_PERFORMED,
                       actionCommand,
                       event.getWhen(),
                       event.getModifiers());
            }
            ((ActionListener)listeners[i+1]).actionPerformed(e);
         }
      }
   }
}   
```

## 事件分发过程

```
actionPerformed:31, SwingNotUsingAction$2 (action)
fireActionPerformed:1972, AbstractButton (javax.swing)
actionPerformed:2313, AbstractButton$Handler (javax.swing)
fireActionPerformed:405, DefaultButtonModel (javax.swing)
setPressed:262, DefaultButtonModel (javax.swing)
mouseReleased:279, BasicButtonListener (javax.swing.plaf.basic)
processMouseEvent:6626, Component (java.awt)
processMouseEvent:3389, JComponent (javax.swing)
processEvent:6391, Component (java.awt)
processEvent:2266, Container (java.awt)
dispatchEventImpl:5001, Component (java.awt)
dispatchEventImpl:2324, Container (java.awt)
dispatchEvent:4833, Component (java.awt)
retargetMouseEvent:4948, LightweightDispatcher (java.awt)
processMouseEvent:4575, LightweightDispatcher (java.awt)
dispatchEvent:4516, LightweightDispatcher (java.awt)
dispatchEventImpl:2310, Container (java.awt)
dispatchEventImpl:2780, Window (java.awt)
dispatchEvent:4833, Component (java.awt)
dispatchEventImpl:773, EventQueue (java.awt)
run:722, EventQueue$4 (java.awt)
run:716, EventQueue$4 (java.awt)
executePrivileged:776, AccessController (java.security)
doPrivileged:399, AccessController (java.security)
doIntersectionPrivilege:86, ProtectionDomain$JavaSecurityAccessImpl (java.security)
doIntersectionPrivilege:97, ProtectionDomain$JavaSecurityAccessImpl (java.security)
run:746, EventQueue$5 (java.awt)
run:744, EventQueue$5 (java.awt)
executePrivileged:776, AccessController (java.security)
doPrivileged:399, AccessController (java.security)
doIntersectionPrivilege:86, ProtectionDomain$JavaSecurityAccessImpl (java.security)
dispatchEvent:743, EventQueue (java.awt)
pumpOneEventForFilters:203, EventDispatchThread (java.awt)
pumpEventsForFilter:124, EventDispatchThread (java.awt)
pumpEventsForHierarchy:113, EventDispatchThread (java.awt)
pumpEvents:109, EventDispatchThread (java.awt)
pumpEvents:101, EventDispatchThread (java.awt)
run:90, EventDispatchThread (java.awt)
```











