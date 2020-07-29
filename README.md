# HyperProperties
HyperProperties API。讓Properties更簡單！！！

HyperProperties API. Make Properties to easy!!!

# Properties Object
BasicHyperProperties

NullableHyperProperties

# BasicHyperProperties

必須在實例化BasicHyperProperties物件時，輸入File型別的變數。

Must be input a file var when you instance a BasicHyperProperties Object.

使用 "HyperProperties (name) = new BasicHyperProperties(File file)"

-> 來實例一個物件。

Using "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object.

使用 "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> 來實例一個物件，並呼叫 createProp(String key, String value) 方法。

Using "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).

使用 "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> 來實例一個物件，並呼叫 createProp(Map<String, String>) 方法。

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
