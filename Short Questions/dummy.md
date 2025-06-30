### Short Questions


part1：
1. 插入 student 表时，department 表还没建好：
在 student 表里定义了 FOREIGN KEY (dept_id) REFERENCES department(dept_id)，但当时还没有创建 department 表。
2. student_id 主键重复
INSERT INTO student (student_id, ...) VALUES
(1002, 'Ziwei Zhang', ...),
(1002, 'Tyler A.', ...);
主键 student_id = 1002 出现了重复，违反了主键唯一性约束。
3. 插入了不存在的 dept_id = 666
4.  删除被引用的 department 时失败
student 表中存在引用 dept_id = 101 的记录，不能删除有子表依赖的父表记录。
5. 重复创建已存在的表
CREATE TABLE school (...);

part2：
# SQL JOIN 类型比较说明

本文件基于以下三张表结构与数据（student, department, school）演示不同 SQL 连接方式的行为差异，包括：

- 手动连接（无 JOIN 关键字）
- INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- FULL JOIN（使用 UNION 实现）

## 🔧 数据准备（前提）

我们已知：

- `student` 表中部分学生没有 `dept_id`（为 NULL）
- `department` 表中的记录都有对应的 `school_id`
- `school` 表中存在与 `department` 匹配的记录

---

## 🧩 为什么需要 JOIN 关键字？

### 传统手动连接：
```sql
FROM student s, department d, school sc
WHERE s.dept_id = d.dept_id AND d.school_id = sc.school_id;

这种写法虽然可用，但：

不直观

容易忘记 WHERE 条件，导致笛卡尔积

无法指定连接类型（如左/右连接）

推荐使用 JOIN 的原因：
可读性高

支持多种 JOIN 类型

更清晰表达表之间的逻辑关系
 INNER JOIN（只显示“有关联”的记录）
 只包含 student 表中 dept_id 非空且在 department 存在的学生。
  LEFT JOIN（保留所有学生）
  所有 student 都会显示出来，即使没有对应的 department。未匹配的 dept_name 显示为 NULL。

用途：当你想“找出哪些学生没有部门”时，非常有用。
RIGHT JOIN
所有 department 都会显示出来，即使没有学生隶属。
FULL JOIN
包含所有 student 和所有 department，即使两边没有匹配。