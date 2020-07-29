# HyperProperties
HyperProperties API。讓Properties更簡單！！！

HyperProperties API. Make Properties to easy!!!

# HyperProperties Object (HypreProperties 物件)
BasicHyperProperties (標準版)

NullableHyperProperties (可允許Null版本)

# BasicHyperProperties (中文)
這個物件會在你嘗試使用空指標的時候，丟出一個Exception並停止方法執行更下一步的操作。例如：getAll()將會丟出FileNotFoundException


必須在實例化BasicHyperProperties物件時，輸入File型別的變數。


推薦：使用 "HyperProperties (name) = new BasicHyperProperties(File file)"

-> 來實例一個物件。


使用 "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> 來實例一個物件，並呼叫 createProp(String key, String value) 方法。


使用 "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> 來實例一個物件，並呼叫 createProp(Map<String, String>) 方法。

# BasicHyperProperties (English)
This object will throw a Exception to console and stop the method when you try to use a Null Pointer.Ex. getAll() will throw FileNotFoundException.

Must be input a file var when you instance a BasicHyperProperties Object.


Recommend: Using "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object.

Using "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).


Using "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).

# NullableHyperProperties (中文)
這個物件將會在你嘗試使用空指標時候，打印出訊息或是回傳一些東西。例如：getAll()將會回傳一個無內容的Map<String, String>

使用 "HyperProperties (name) = new NullableHyperProperties()"

-> 來實例一個物件。
-----> 如果你使用這個建構子，你必須使用 (name).setFile(File file) 來避免空指標

推薦：使用 "HyperProperties (name) = new NullableHyperProperties(File file)"

-> 來實例一個物件並設定File。


使用 "HyperProperties (name) = new NullableHyperProperties(File file, String key, String value)"

-> 來實例一個物件，並呼叫 createProp(String key, String value) 方法。


使用 "HyperProperties (name) = new NullableHyperProperties(File file, Map<String, String>)"

-> 來實例一個物件，並呼叫 createProp(Map<String, String>) 方法。

# NullableHyperProperties (English)
This object will print or return something when you try to use a Null Pointer. Ex. getAll() will return the Map<String, String> but not have anything.


Using "HyperProperties (name) = new NullableHyperProperties()"

-> To create the object.

-----> If you use this constuctor, You need to use (name).setFile(File file) to avoid null pointer.

Recommend: Using "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object with file.

Using "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).

Using "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).

# 製作自己的HyperProperties
請實作HyperProperties這個界面在你的類別中。


你至少需要實做16種方法在你的類別中。


你也可以實做toString()方法。建議你使用 "return getAll().toString" 來實做。


當你完成後，使用"HyperProperties (name) = new (Your HyperProperties Class)"來實例物件，並且希望一切順利。

如果你認為你的HyperProperties物件寫得很棒，並希望它出現在這個API中。

聯絡我的電子郵件：zhikuan0322@gmail.com

# Making own HyperProperties
Please implements HyperProperties Interface to your class.

You must implement at least 16 methods in your class.

You can also override the toString() method. Recommend you using "return getAll().toString" to override the method.

When you completed, use "HyperProperties (name) = new (Your HyperProperties Class)" to instance the object and hope everything goes well.

If you think your HyperProperties Object is well, And want it add to this API.

Contact my email: zhikuan0322@gmail.com
