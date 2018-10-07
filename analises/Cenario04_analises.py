
# coding: utf-8

# In[1]:

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns


# In[11]:

slinear = pd.read_csv('../../../data/saida_SLinear.csv')
sQuad = pd.read_csv('../../../data/saida_SQuad.csv')
duplo_hash = pd.read_csv('../../../data/saida_DuploHash.csv')
eSeparado = pd.read_csv('../../../data/saida_ESeparado.csv')
eCoalescido = pd.read_csv('../../../data/saida_ECoalescido.csv')


# In[12]:

group_slinear = slinear.groupby('tam').mean()
group_sQuad = sQuad.groupby('tam').mean()
group_duplo_hash = duplo_hash.groupby('tam').mean()
group_eSeparado = eSeparado.groupby('tam').mean()
group_eCoalescido = eCoalescido.groupby('tam').mean()


# In[13]:

plt.plot(group_slinear.index,group_slinear.duracao, label='Sond. Linear')
plt.plot(group_sQuad.index,group_sQuad.duracao, label='Sond. Quadrática')
plt.plot(group_duplo_hash.index,group_duplo_hash.duracao, label='Duplo Hash')
plt.plot(group_eSeparado.index,group_eSeparado.duracao, label='Encad. Separado')
plt.plot(group_eCoalescido.index,group_eCoalescido.duracao, label='Encad. Coalescido')

plt.title('Duração média de Inserção')
plt.xlabel('Quantidade de dados')
plt.ylabel('Duração [ms]')
plt.yscale('log')
plt.legend()
plt.savefig('../../images/cenario04/duracao.png')
plt.show()


# In[14]:

plt.plot(group_slinear.index,group_slinear.num_comparacao, label='Sond. Linear')
plt.plot(group_sQuad.index,group_sQuad.num_comparacao, label='Sond. Quadrática')
plt.plot(group_duplo_hash.index,group_duplo_hash.num_comparacao, label='Duplo Hash')
plt.plot(group_eSeparado.index,group_eSeparado.num_comparacao, label='Encad. Separado')
plt.plot(group_eCoalescido.index,group_eCoalescido.num_comparacao, label='Encad. Coalescido')

plt.title('Número médio de comparações')
plt.xlabel('Quantidade de dados')
plt.ylabel('Comparações')
plt.yscale('log')
plt.legend()
plt.savefig('../../images/cenario04/comparacao.png')
plt.show()


# In[15]:

plt.plot(group_slinear.index,group_slinear.memoria, label='Sond. Linear')
plt.plot(group_sQuad.index,group_sQuad.memoria, label='Sond. Quadrática')
plt.plot(group_duplo_hash.index,group_duplo_hash.memoria, label='Duplo Hash')
plt.plot(group_eSeparado.index,group_eSeparado.memoria, label='Encad. Separado')
plt.plot(group_eCoalescido.index,group_eCoalescido.memoria, label='Encad. Coalescido')

plt.title('Memória utilizada média')
plt.xlabel('Quantidade de dados')
plt.ylabel('Memória')
plt.yscale('log')
plt.legend()
plt.savefig('../../images/cenario04/memoria.png')
plt.show()


# In[ ]:



