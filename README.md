# HyperProperties
HyperProperties API

# Properties Object
BasicHyperProperties

NullableHyperProperties

# BasicHyperProperties

Must be input a file var when you new BasicHyperProperties Object.

Use "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object.

Use "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).

Use "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).

# NullableHyperProperties
Use "HyperProperties (name) = new NullableHyperProperties()"

-> To create the object.

-----> If you use this constuctor, You need to use (name).setFile(File file).

Use "HyperProperties (name) = new BasicHyperProperties(File file)"

-> To create the object with file.

Use "HyperProperties (name) = new BasicHyperProperties(File file, String key, String value)"

-> To create the object and call method createProp(String key, String value).

Use "HyperProperties (name) = new BasicHyperProperties(File file, Map<String, String>)"

-> To create the object and call method createProp(Map<String, String>).
