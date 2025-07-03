1. The equals() method is used to determine if two objects are logically equal, based on their content or the attributes that define their identity. It checks for reference equality (whether two references point to the exact same object in memory)
2. The hashCode() method returns an integer value (hash code) that represents an object's data based on the object's memory address.

If two objects are equal according to equals(), their hashCode() must return the same integer value.