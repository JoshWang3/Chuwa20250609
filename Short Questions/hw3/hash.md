HashMap and HashTable both implements the Map interface and use hasing to store key-value pairs but 
1. HashTable has all synchronized methods and thus is thread-safe (better for multi-threading) whereas HashMap is not (better for single thread).
2. HashTable prevents null values (throws NPE) whereas HashMap permits 1 null key and multiple null values.
3. HashMap is generally faster due no synchronization.