-- Добавление нового столбца task_limit
ALTER TABLE columns ADD COLUMN task_limit INT;

-- Установка значения по умолчанию для уже существующих строк
UPDATE columns SET task_limit = 10 WHERE task_limit IS NULL;

-- (Опционально) Установка ограничения NOT NULL, если нужно
ALTER TABLE columns ALTER COLUMN task_limit SET NOT NULL;
