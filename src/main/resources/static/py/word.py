from docx import Document
from docx.shared import RGBColor, Pt
from docx.oxml.ns import qn
import pymysql
import ast

db = pymysql.connect(host="localhost", user="root", password="0000", database="db")
cursor = db.cursor()
document = Document('学校模板.docx')
document.styles['Normal'].font.name = 'Times New Roman'
document.styles['Normal']._element.rPr.rFonts.set(qn('w:eastAsia'), u'宋体')
document.styles['Normal'].font.size = Pt(12)
document.styles['Normal'].font.color.rgb = RGBColor(0, 0, 0)
questionNumber = 1
op = ['A', 'B', 'C', 'D']
opNum = 0

def getQuestions(id):
    sql = f"select description from tb_questions where id = {id}"
    cursor.execute(sql)
    return cursor.fetchall()[0]

def getOptions(id):
    sql = f"select options from tb_questions where id = {id}"
    cursor.execute(sql)
    options = ast.literal_eval(cursor.fetchall()[0][0])
    return options

def addSelectQuestion(list):
    global questionNumber
    global op
    global opNum
    for id in list:
        question = getQuestions(id)#获取问题
        optionsList = getOptions(id)#获取选项列表
        subject = document.add_paragraph(f'{questionNumber}、{question[0]}')#插入题目，questionNumber是题号
        subject.paragraph_format.line_spacing = Pt(22)#题目的行距
        optionParagraph = document.add_paragraph('')#插入选项段落
        optionParagraph.paragraph_format.line_spacing = Pt(22)  # 选项的行距
        for optionNum, option in zip(op, optionsList):# 循环插入选项
            optionParagraph.add_run(f"{optionNum}、{option}   ")
        questionNumber += 1

def addQuestion(list):
    global questionNumber
    for id in list:
        question = getQuestions(id)  # 获取问题
        subject = document.add_paragraph(f'{questionNumber}、{question[0]}')  # 插入题目，questionNumber是题号
        subject.paragraph_format.line_spacing = Pt(22)  # 题目的行距
        questionNumber += 1


if __name__ == '__main__':
    selectList = ['1', '6', '11', '39', '42']
    gapArray = ['2', '44']
    answerArray = ['40', '45', '43', '41']

    point1 = document.add_picture('得分.bmp')
    p1 = document.add_paragraph('')
    p1.add_run('一、单项选择题（在括号里填入正确答案，每小题 2 分，共30 分）').bold = True
    addSelectQuestion(selectList)

    point2 = document.add_picture('得分.bmp')
    p2 = document.add_paragraph('')
    p2.add_run('二、填空题（在横线上填上正确答案，每空 2 分，共 20 分）').bold = True
    addQuestion(gapArray)

    point3 = document.add_picture('得分.bmp')
    p3 = document.add_paragraph('')
    p3.add_run('三、将下列逻辑函数化为最简与或式：（方法不限，写出化简步骤，每小题 5 分，共 15 分。）').bold = True
    addQuestion(answerArray)

    document.save("over.docx")
    cursor.close()
    db.close()
    print('finish')
