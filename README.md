# HyperProperties
HyperProperties API。讓Properties更簡單！！！

HyperProperties API. Make Properties to easy!!!

# HyperProperties Object (HypreProperties 物件)
BasicHyperProperties (標準版)

NullableHyperProperties (可允許Null版本)

# BasicHyperProperties (中文)

必須在實例化BasicHyperProperties物件時，輸入File型別的變數。


使用 "HyperProperties (name) = new BasicHyperProperties(File file)"

-> 來實例一個物件。


使用 "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> 來實例一個物件，並呼叫 createProp(String key, String value) 方法。


使用 "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> 來實例一個物件，並呼叫 createProp(Map<String, String>) 方法。

# BasicHyperProperties (English)

Must be input a file var when you instance a BasicHyperProperties Object.


Using "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object.

Using "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).


Using "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).

# NullableHyperProperties
Using "HyperProperties (name) = new NullableHyperProperties()"

-> To create the object.

-----> If you use this constuctor, You need to use (name).setFile(File file).

Using "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object with file.

Using "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).

Using "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).
